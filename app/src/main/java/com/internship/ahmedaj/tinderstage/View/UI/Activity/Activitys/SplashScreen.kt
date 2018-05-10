package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.ViewModel.LoginActivityViewModel
import com.internship.ahmedaj.tinderstage.ViewModel.SplashScreenViewModel

class SplashScreen : AppCompatActivity() {
    private lateinit  var mAuth: FirebaseAuth
    //view model login
    private lateinit var splashviewmodel:SplashScreenViewModel

    //intent
    lateinit var intentcandrect:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
       mAuth = FirebaseAuth.getInstance()
        splashviewmodel= ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        intentcandrect=Intent(applicationContext, MainActivity::class.java)
       updateUI(mAuth.currentUser)
        Handler().postDelayed(object: Runnable {
             override fun run() {
         //      val i:Intent =  Intent(applicationContext, Login::class.java)
           //    startActivity(i)
                 // close this activity
         // updateUI(mAuth.currentUser)
                 finish()
             }

        }, 2000);
    }
    fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser==null){

            Handler().postDelayed(object: Runnable {
                override fun run() {
                    //      val i:Intent =  Intent(applicationContext, Login::class.java)
                    //    startActivity(i)
                    // close this activity
                    // updateUI(mAuth.currentUser)
                    Toast.makeText(this@SplashScreen, "No user connected",
                            Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SplashScreen,Login::class.java))
                    finish()
                }

            }, 1000);

        }else{

            Toast.makeText(this, "current user is :"+currentUser.email,
                    Toast.LENGTH_SHORT).show()

            splashviewmodel.getCandByEmail(currentUser.email!!).observe(this,object: Observer<Candidate> {
                override fun onChanged(t: Candidate?) {
                    Log.d("in get cond","email")
                    if(t!=null)
                    {
                        Log.d("in get cond not null","email")
                        intentcandrect.putExtra("accountType","Candidate")
                        startActivity(intentcandrect)

                    }else {
                        splashviewmodel.getRectByEmail(currentUser.email!!).observe(this@SplashScreen, object : Observer<Recruter> {
                            override fun onChanged(t: Recruter?) {
                                Log.d("in get rect","email")
                                if(t!=null){
                                    Log.d("in get rect not null","email")
                                    intentcandrect.putExtra("accountType","Recruter")
                                    startActivity(intentcandrect)
                                }else
                                {
                                    Log.d("cand null rect null","start login")
                                    mAuth.signOut()
                                    Toast.makeText(this@SplashScreen, "plz choose your connection type", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@SplashScreen, Login::class.java))

                                }
                            }

                        })

                    }
                }
            })

        }

    }


}

