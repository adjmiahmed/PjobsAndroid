package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.internship.ahmedaj.tinderstage.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
         Handler().postDelayed(object: Runnable {
             override fun run() {
                 val i:Intent =  Intent(applicationContext, MainActivity::class.java)
                 startActivity(i)
                 // close this activity
                 finish()
             }

        }, 2000);
    }

}

