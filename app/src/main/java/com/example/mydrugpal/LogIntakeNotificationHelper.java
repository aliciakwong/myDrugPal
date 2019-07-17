package com.example.mydrugpal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/**
 * The LogIntakeNotificationHelper creates the channel for notifications, the notification manager,
 * and builds a notification object. [RP]
 * Good info on building notification objects is here:
 * https://developer.android.com/training/notify-user/build-notification.html
 * This class is based on: https://codinginflow.com/tutorials/android/alarmmanager
 * NOTE: This class does not need to be in the manifest.
 * NOTE: The imports need the dependency: implementation "com.android.support:support-compat:28.0.0"
 */
public class LogIntakeNotificationHelper extends ContextWrapper {
    /**
     * The channel to broadcast notifications on.
     */
    public static final String channelID = "channelID";
    /**
     * A human readable name for the channel.
     */
    public static final String channelName = "Channel Name";
    /**
     * A variable for printing to logcat. [RP]
     * To find any of these tags enter "Debug Printing" in the logcat search field
     * while the app is running.
     */
    private static final String TAG = "Debug Printing";

    /**
     * As it says, this is the manager of notifications.
     */
    private NotificationManager mManager;

    /**
     * Creates the notification channel for this launch of the app.
     * @param base
     */
    public LogIntakeNotificationHelper(Context base) {
        super(base);
        Log.d(TAG,"Hello from LogIntakeNotificationHelper");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    /**
     * Before notifications can be recieved a channel has to be created.
     * This is done once when the alarm is started through the SubstanceSummaryActivity.
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        Log.d(TAG,"Hello from createChannel");
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    /**
     * As the title says, this is the class that manages notifications.
     * @return
     */
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    /**
     * This is where notifications are built on the fly.
     * It is typically called from LogIntakeNotificationReceiver.
     * @return
     */
    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Log Intake Alert?")
                .setContentText("Have you added to your intake diary today?")
                .setSmallIcon(R.drawable.ic_announcement);
    }
}

