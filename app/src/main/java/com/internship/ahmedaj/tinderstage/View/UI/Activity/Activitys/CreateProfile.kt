package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.internship.ahmedaj.tinderstage.R
import kotlinx.android.synthetic.main.activity_create_profile.*

class CreateProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        bt_Candidate.setOnClickListener {
            startActivity(Intent(this, CandidateProfileAct::class.java))
        }

        bt_Recruteur.setOnClickListener {

            startActivity(Intent(this, RecruterProfileAct::class.java))

        }
    }
}
