package com.internship.ahmedaj.tinderstage.Service.fireBaseService

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import com.internship.ahmedaj.tinderstage.Service.Repository.NotificationRepository


/**
 * Created by ahmed aj on 23/05/2018.
 */
class MyFirebaseInstanceIDService: FirebaseInstanceIdService() {

var notifcationrep:NotificationRepository= NotificationRepository()
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("Token", "Refreshed token: " + refreshedToken!!)

        // Instance ID token to  app server.
   //   sendRegistrationToServer(refreshedToken)

    }

    fun sendRegistrationToServer(token:String){
        var s=notifcationrep.sendToken(token)
        Log.d("msg rept token",s.value)
    }
}