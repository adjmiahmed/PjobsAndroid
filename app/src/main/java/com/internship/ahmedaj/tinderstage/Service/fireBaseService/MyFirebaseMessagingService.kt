package com.internship.ahmedaj.tinderstage.Service.fireBaseService

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys.NavDrowarAct

/**
 * Created by ahmed aj on 29/05/2018.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        var count:Int=0
        var accfrag:String=""
     //   var account:String=""
    }


    lateinit var sharedPrefAcc: SharedPreferences
    var gson= Gson()

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d("GCM", "From: " + remoteMessage!!.getFrom())

// Check if message contains a data payload.
        if (remoteMessage.getData().size > 0)
        {  Log.d("GCM", "Message data payload: " + remoteMessage.getData())
            accfrag= remoteMessage.data!!["Activity"].toString()
            Log.d("acccccccc",accfrag)
            //   saveListInSharedPref()
        }

//notificaiton payload
        if (remoteMessage.getNotification() != null) {
            Log.d("GCM", "Message Notification Body: " + remoteMessage.getNotification()!!.getBody());

            sendNotification(remoteMessage.getNotification()!!.title!!,
                    remoteMessage.getNotification()!!.getBody()!!, remoteMessage.getData())

        }

    }
    private fun sendNotification(messageTitle: String,messageBody: String ,  row:Map<String, String>) {
        var  contentIntent: PendingIntent?= null;
        var notificationIntent: Intent = Intent(applicationContext, NavDrowarAct::class.java)
        Log.d("bacbacb",accfrag)
        notificationIntent.putExtra("nextfrag",accfrag)
        var pendingintent= PendingIntent.getActivity(applicationContext, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        var defaultSoundUri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        var notificationBuilder : NotificationCompat.Builder= object: NotificationCompat.Builder(this){}
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logoproxym))
                .setSmallIcon(R.drawable.pjobs)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingintent);
        var  notificationManager: NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(count, notificationBuilder.build());
        count++;
    }


    override fun onDeletedMessages() {
        super.onDeletedMessages()

    }


}