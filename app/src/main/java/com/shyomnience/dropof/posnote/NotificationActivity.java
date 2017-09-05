package com.shyomnience.dropof.posnote;

import android.support.v7.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {


  //  private Button button;
   // public static int NOTIFICATION_ID = 1;
   // public static final String KEY_NOTIFICATION_REPLY = "KEY_NOTIFICATION_REPLY";

/*
   public void NotificationActivity(){


            // Create PendingIntent to take us to DetailsActivity
            // as a result of notification action
            Intent detailsIntent = new Intent(NotificationActivity.this, DetailsActivity.class);
            detailsIntent.putExtra("EXTRA_DETAILS_ID", 42);
            PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                    NotificationActivity.this,
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
                        NotificationActivity.this,
                        0,
                        new Intent(NotificationActivity.this, ReplyReceiver.class),
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
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationActivity.this)
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

            // Obtain NotificationManager system service in order to show the notification

                   /*
                    .setStyle(new NotificationCompat.MessagingStyle("Me")
                            .setConversationTitle("Example Playground Chat")
                            .addMessage("Lorem", 1, null) // null means device's user
                            .addMessage("ipsum", 2, "Bot")
                            .addMessage("dolor", 3, "Bot")
                            .addMessage("sit amet", 4, null));*/

/*
            // Obtain NotificationManager system service in order to show the notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
          notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        }*/

}
