package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.internship.ahmedaj.tinderstage.R
import kotlinx.android.synthetic.main.activity_cand_rect.*

class CandRect : AppCompatActivity() {
private lateinit var bt_cand: ImageView
    private lateinit var bt_rect:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cand_rect)
bt_cand=imgcandidate
        bt_rect=imgrecuteur


        bt_cand.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        bt_rect.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
