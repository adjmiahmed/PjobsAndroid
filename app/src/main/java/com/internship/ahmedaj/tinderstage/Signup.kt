package com.internship.ahmedaj.tinderstage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.internship.ahmedaj.tinderstage.R.id.progressFix
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    private lateinit var signup: CircularProgressButton
    private lateinit var tx_email: EditText
    private lateinit var tx_passwd: EditText
    private lateinit  var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //initalisation
        tx_email=ed_email
        tx_passwd=ed_passwd
        signup=bt_signup
        //firebase auth
         mAuth = FirebaseAuth.getInstance()

        //on click on signup
        signup.setOnClickListener{
            Toast.makeText(this, "Button click",
                    Toast.LENGTH_SHORT).show()
            if(tx_email.text.toString()!=null)
                if(tx_passwd.text.toString()!=null)
            // create user firebase
                { mAuth.createUserWithEmailAndPassword(tx_email.text.toString(),tx_passwd.text.toString() )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            animateButtonAndRevert(bt_signup,
                                    ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark), BitmapFactory.decodeResource(resources, R.drawable.checkmark) )

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "createUserWithEmail:success")
                            Toast.makeText(this, "create with sucess .",
                                    Toast.LENGTH_SHORT).show()
                            val user = mAuth.currentUser
                            updateUI(user)
                            startActivity(Intent(this, Login::class.java))

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("aaaaaaaaaaaaa", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, "add fail .",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }

                        // ...
                    }
        }else{
                    Toast.makeText(this, "plz enter a valid email or password.",
                            Toast.LENGTH_SHORT).show()
                }

        }

    }

    private fun updateUI(user: FirebaseUser?) {

        if(user==null){
            Log.d("null","no user connected")
        }else{
            Log.d("Current user",user.email)
        }

    }



    //annimation methode
    private fun animateButtonAndRevert(circularProgressButton: CircularProgressButton, fillColor: Int, bitmap: Bitmap) {
        val handler = Handler()

        val runnable = {
            circularProgressButton.doneLoadingAnimation(
                    fillColor,
                    bitmap)
        }

       /* val changeActivity = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/

        circularProgressButton.revertAnimation()

        circularProgressButton.startAnimation()
        handler.postDelayed(runnable, 3000)
    //    handler.postDelayed(changeActivity, 4000)
      //  handler.postDelayed(changeActivity, 4100)
    }



}
