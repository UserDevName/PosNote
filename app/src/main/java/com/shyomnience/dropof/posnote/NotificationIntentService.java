package com.shyomnience.dropof.posnote;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import static com.shyomnience.dropof.posnote.MainActivity.KEY_NOTIFICATION_REPLY;


public class NotificationIntentService extends IntentService {

   private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";


    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
            if (ACTION_DELETE.equals(action)) {
                processDeleteNotification(intent);
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?

       // final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
       // builder.setContentTitle("Scheduled Notification")
       //         .setAutoCancel(true)
       //         .setColor(getResources().getColor(R.color.colorAccent))
        //        .setContentText("This notification has been triggered by Notification Service")
       //         .setSmallIcon(R.drawable.notification_icon);

      //  PendingIntent pendingIntent = PendingIntent.getActivity(this,
      //          NOTIFICATION_ID,
      //          new Intent(this, NotificationActivity.class),
       //         PendingIntent.FLAG_UPDATE_CURRENT);


        // Create PendingIntent to take us to DetailsActivity
        // as a result of notification action
        Intent detailsIntent = new Intent(NotificationIntentService.this, DetailsActivity.class);
        detailsIntent.putExtra("EXTRA_DETAILS_ID", 42);
        PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                NotificationIntentService.this,
                0,
                detailsIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Define PendingIntent for Reply action
        PendingIntent replyPendingIntent = null;
        // Call Activity on platforms that don't support DirectReply natively
        if (Build.VERSION.SDK_INT < 24) {
            replyPendingIntent = detailsPendingIntent;
        } else { // Call BroadcastReceiver on platforms supporting DirectReply
            replyPendingIntent = PendingIntent.getBroadcast(
                    NotificationIntentService.this,
                    0,
                    new Intent(NotificationIntentService.this, ReplyReceiver.class),
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
        }

        // Create RemoteInput and attach it to Notification Action
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_NOTIFICATION_REPLY)
                .setLabel("Reply")
                .build();
        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_save, "Provide ID", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        // NotificationCompat Builder takes care of backwards compatibility and
        // provides clean API to create rich notifications
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationIntentService.this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Something important happened")
                .setContentText("See the details")
                .setAutoCancel(true)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(detailsPendingIntent)
                .addAction(replyAction)
                .addAction(android.R.drawable.ic_menu_compass, "Details", detailsPendingIntent)
                .addAction(android.R.drawable.ic_menu_directions, "Show Map", detailsPendingIntent);

        mBuilder.setContentIntent(detailsPendingIntent);
       mBuilder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        // Obtain NotificationManager system service in order to show the notification

                  /*
                    .setStyle(new NotificationCompat.MessagingStyle("Me")
                            .setConversationTitle("Example Playground Chat")
                            .addMessage("Lorem", 1, null) // null means device's user
                            .addMessage("ipsum", 2, "Bot")
                            .addMessage("dolor", 3, "Bot")
                            .addMessage("sit amet", 4, null));*/


            // Obtain NotificationManager system service in order to show the notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

       // final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
      //  manager.notify(NOTIFICATION_ID, builder.build());
    }
}