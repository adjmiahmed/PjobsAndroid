package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandItemAcceptedList
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.ViewModel.LoginActivityViewModel
import com.internship.ahmedaj.tinderstage.ViewModel.SplashScreenViewModel
import java.lang.reflect.Type

class SplashScreen : AppCompatActivity() {
    private lateinit  var mAuth: FirebaseAuth
    //view model login
    private lateinit var splashviewmodel:SplashScreenViewModel
    var account:String=""
    lateinit var sharedPrefAcc: SharedPreferences
    var gson= Gson()
    //intent
    lateinit var intentcandrect:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
       mAuth = FirebaseAuth.getInstance()
        splashviewmodel= ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        intentcandrect=Intent(applicationContext, NavDrowarAct::class.java)

        sharedPrefAcc = getSharedPreferences("Account", Context.MODE_PRIVATE)

        updateUI(mAuth.currentUser)
        }
    fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser==null){

            Handler().postDelayed(object: Runnable {
                override fun run() {
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
                    {account="Candidate"
                        Log.d("in get cond not null","email")
                       // intentcandrect.putExtra("accountType","Candidate")
                        saveListInSharedPref()
                        startActivity(intentcandrect)

                    }else {
                        splashviewmodel.getRectByEmail(currentUser.email!!).observe(this@SplashScreen, object : Observer<Recruter> {
                            override fun onChanged(t: Recruter?) {
                                Log.d("in get rect","email")
                                if(t!=null){
                                    account="Recruter"
                                    Log.d("in get rect not null","email")
                                    //intentcandrect.putExtra("accountType","Recruter")
                                    saveListInSharedPref()
                                    startActivity(intentcandrect)
                                }else
                                {
                                    Log.d("cand null rect null","start Creating account")
                                    saveListInSharedPref()
                                    startActivity(Intent(this@SplashScreen, CreateProfile::class.java))

                                }
                            }

                        })

                    }
                }
            })

        }

    }


    fun saveListInSharedPref(){
        val edit=sharedPrefAcc.edit()
        val json= gson.toJson( account)
        edit.putString("accountType",json)
        edit.apply()
    }

}

