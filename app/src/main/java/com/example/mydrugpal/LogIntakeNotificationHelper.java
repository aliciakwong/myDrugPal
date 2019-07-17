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
 * and builds a notification object. Ensure that the imports have the dependency implementation "com.android.support:support-compat:28.0.0"
 * @see <a href = "https://developer.android.com/training/notify-user/build-notification.html" />
 * @see <a href = "https://codinginflow.com/tutorials/android/alarmmanager"/>
 *
 * @author Richard Purcell, Ian Sifton
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

    private static final String TAG = "Debug Printing";


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

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        Log.d(TAG,"Hello from createChannel");
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    /**
     * As the title says, this is the class that manages notifications.
     * @return mManager returns the notification manager
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
     * @return a new NotificationComp.Builder object
     */
    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Log Intake Alert?")
                .setContentText("Have you added to your intake diary today?")
                .setSmallIcon(R.drawable.ic_announcement);
    }
}

