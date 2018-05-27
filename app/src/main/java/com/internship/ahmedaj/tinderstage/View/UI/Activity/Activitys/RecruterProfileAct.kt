package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.RecruterEntreprise
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.RecruterNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.RecruterOffer
import com.internship.ahmedaj.tinderstage.ViewModel.EditProfileViewModel
import kotlinx.android.synthetic.main.activity_recruter_profile.*
import java.text.SimpleDateFormat
import java.util.*

class RecruterProfileAct : AppCompatActivity() {
    var formatter: SimpleDateFormat = SimpleDateFormat("yyyy")
    lateinit var offers: ArrayList<RecruterOffer>
    lateinit var editprof: EditProfileViewModel
    var recruterProfile:RecruterNoId?=null
   lateinit var intentRect:Intent
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recruter_profile)
        editprof = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
        offers= ArrayList<RecruterOffer>()
intentRect=Intent(this@RecruterProfileAct,NavDrowarAct::class.java)
        intentRect.putExtra("accountType","Recruter")
        bt_saveprofile.setOnClickListener {
    recruterProfile= RecruterNoId(tx_username.text.toString(),mAuth.currentUser!!.email!!,"",formatter.parse(tx_datenaissVal.text.toString()),Integer.parseInt(tx_Phoneval.text.toString()),tx_adressval.text.toString(),"","",offers, RecruterEntreprise(tx_EntrepriseName.text.toString(),Integer.parseInt(tx_EntreprisePhone.text.toString()),tx_EntrepriseAdress.text.toString(),"ggggg",tx_EntrepriseEmail.text.toString(),"",tx_EntrepriseSite.text.toString()))
            //create profile
            editprof.createRecruter(recruterProfile!!).observe(this@RecruterProfileAct, object : android.arch.lifecycle.Observer<Recruter> {
                override fun onChanged(t: Recruter?) {
                    if (t != null)
                    { Toast.makeText(applicationContext, "Profile Created with sucess", Toast.LENGTH_SHORT).show()
                  startActivity(intentRect)}
                    else
                        Toast.makeText(applicationContext, "Fail to create profile with sucess", Toast.LENGTH_SHORT).show()
                }


            })

}



    }
}
