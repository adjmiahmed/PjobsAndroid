package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.internship.ahmedaj.tinderstage.R


/**
 * Created by ahmed aj on 23/05/2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

companion object {
    var count:Int=0
}
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d("GCM", "From: " + remoteMessage!!.getFrom())

// Check if message contains a data payload.
        if (remoteMessage.getData().size > 0)
        {  Log.d("GCM", "Message data payload: " + remoteMessage.getData())

            sendNotification("aaaa",
                    "bbb", remoteMessage.getData())
        }

//notificaiton payload
        if (remoteMessage.getNotification() != null) {
            Log.d("GCM", "Message Notification Body: " + remoteMessage.getNotification()!!.getBody());
        }

    }
    private fun sendNotification(messageTitle: String,messageBody: String ,  row:Map<String, String>) {
      var  contentIntent: PendingIntent?= null;
        var defaultSoundUri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        var notificationBuilder : NotificationCompat.Builder= object: NotificationCompat.Builder(this){}
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logoproxym))
                .setSmallIcon(R.drawable.pjobs)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);
        var  notificationManager:NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(count, notificationBuilder.build());
        count++;
    }


    override fun onDeletedMessages() {
        super.onDeletedMessages()

    }
}




