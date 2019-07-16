package com.example.mydrugpal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mydrugpal.model.CurrentUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * This class handles the receiving parts of notifications. [RP]
 * On Receiving a notification from a channel it builds a notification object and displays it.
 * This class is based on: https://codinginflow.com/tutorials/android/alarmmanager
 * NOTE: This class has been added to the manifest.
 * NOTE: The imports need the dependency: implementation "com.android.support:support-compat:28.0.0"
 */
public class LogIntakeNotificationReceiver extends BroadcastReceiver {
    /**
     * A variable for printing to logcat. [RP]
     * To find any of these tags enter "Debug Printing" in the logcat search field
     * while the app is running.
     */
    private static final String TAG = "Debug Printing";

    /**
     * When the alarm goes off a notification is sent.
     * This method builds everything else that is needed.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Hello from onRecieve");

        checkLatestDateNotification(context, intent);
    }

    /**
     * This method runs the notification if hasIntakeToday returns true
     *
     * @param context
     * @param intent
     */
    public void runNotification(Context context, Intent intent) {
        LogIntakeNotificationHelper notificationHelper = new LogIntakeNotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }


    /**
     * A method for comparing the current date with the dates of substance entries.
     * Currently does not work.
     */
    private boolean hasIntakeToday(Timestamp lastEntry) {
        boolean intakeEntryRequired = false;
        Date date = new Date();
        Timestamp todayTime = new Timestamp(date);
        Timestamp latestEntryDate = lastEntry;


        Log.d(TAG, "Previous Entry: " + latestEntryDate);
        Log.d(TAG, "Today: " + todayTime);

        long diffInSeconds = todayTime.getSeconds() - latestEntryDate.getSeconds();
        Log.d(TAG, "Difference in Milliseconds: " + diffInSeconds);

        long twelveHoursInSeconds = 60 * 60 * 12;
        if (diffInSeconds > twelveHoursInSeconds) {
            intakeEntryRequired = true;
            // fire notification
            Log.d(TAG, "Result of comparison: " + (diffInSeconds > twelveHoursInSeconds));
        }

        return intakeEntryRequired;
    }


    private void checkLatestDateNotification(final Context context, final Intent intent) {

        // Firestore call for latest entry
        CollectionReference diaryList = getColRefFromFirestore();

        Query diaryByDateForLatest = diaryList.orderBy("dateTime", Query.Direction.DESCENDING).limit(1);
        diaryByDateForLatest.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            /**
             * method called to retrieve users from database and update UserList instance with users
             *
             * @param task task to ensure database is properly accessed and data retrieved
             */
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> diaryByDateForLatest = task.getResult().getDocuments();
                    DocumentSnapshot documentResult = diaryByDateForLatest.get(0);

                    Timestamp latestEntry = documentResult.getTimestamp("dateTime");
                    Log.d(TAG, "latestEntryDate: " + latestEntry);

                    if (hasIntakeToday(latestEntry)) {
                        runNotification(context, intent);
                    }
                }
            }
        });
    }


    private CollectionReference getColRefFromFirestore() {
        // Firestore call for latest entry
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference users = db.collection("Users").
                document(CurrentUser.getInstance().GetEmail());
        CollectionReference colRef = users.collection("IntakeDiary");

        return colRef;
    }

}
