package com.project.danielbachelor;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.project.danielbachelor.anmeldung.anmeldungActivity;

//angelehnt an: https://github.com/firebase/quickstart-android/blob/320f5fb45f155de3daf8b997c3788a4a187a024d/messaging/app/src/main/java/com/google/firebase/quickstart/fcm/java/MyFirebaseMessagingService.java#L58-L101
public class MyFirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size() >0){
            String HWSName = remoteMessage.getData().get(getString(R.string.FCM_HWSName));
            String BGrad = remoteMessage.getData().get(getString(R.string.FCM_BrGrad));
            String LGrad = remoteMessage.getData().get(getString(R.string.FCM_LaeGrad));

            String BenachrichtigungString = getString(R.string.fcm_body_1) + HWSName +
                    getString(R.string.fcm_body_2) + BGrad +
                    getString(R.string.fcm_body_3) + LGrad;

            sendNotification(BenachrichtigungString);
        }
    }

    private void sendNotification(String messageBody){
        Intent mIntent = new Intent(this, anmeldungActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_ONE_SHOT);

        String channelID = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID)
                .setContentTitle(getString(R.string.fcm_message))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(mPendingIntent);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelID, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
}
