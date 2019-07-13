package com.example.mydrugpal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

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
        LogIntakeNotificationHelper notificationHelper = new LogIntakeNotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }
}
