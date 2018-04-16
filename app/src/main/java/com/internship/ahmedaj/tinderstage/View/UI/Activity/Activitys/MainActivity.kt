package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ImageButton
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.*
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.ChatFrag
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.HomeFrag
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.OffersFrag
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.ProfileFrag


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottombar.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
private lateinit var homeFrag: HomeFrag
    private lateinit var profileFrag: ProfileFrag
    private lateinit var offersFrag: OffersFrag
    private lateinit var chatFrag: ChatFrag
    private lateinit var toolbar: Toolbar
private lateinit var fmanager:FragmentManager
private lateinit var homeButton:ImageButton
    private lateinit var profileButton:ImageButton
    private lateinit var offersButton:ImageButton
    private lateinit var chatButton:ImageButton
    private lateinit var settingButton:ImageButton
    private lateinit  var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
  var x:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //initialisation
        toolbar= app_bar as Toolbar
        setSupportActionBar(toolbar)
        homeButton=bt_accueil
        profileButton=bt_user
        offersButton=bt_offers
        chatButton=bt_chat
        settingButton=bt_setting
        //firebase auth
        mAuth = FirebaseAuth.getInstance()
        var gso   = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        settingButton.setOnClickListener{
            signOut()
            googleSignOut()
            facebookSignOut()
            startActivity(Intent(this, Login::class.java))
        }
        selectFrag(x)
        homeButton.setOnClickListener { x=0
            Log.d("home","clicked")
            selectFrag(x)}
        offersButton.setOnClickListener { x=1
            Log.d("profile","clicked")
            selectFrag(x)}
       profileButton.setOnClickListener { x=2
           Log.d("offers","clicked")
           selectFrag(x)}
        chatButton.setOnClickListener { x=3
            Log.d("chat","clicked")
            selectFrag(x)}




        //findViewById<View>(R.id.rejectBtn).setOnClickListener { mSwipeView!!.doSwipe(false) }

        //findViewById<View>(R.id.acceptBtn).setOnClickListener { mSwipeView!!.doSwipe(true) }




    }
    //signout methode
    private fun signOut() {
        mAuth.signOut()

    }
    private fun googleSignOut() {
        // Firebase sign out
        mAuth.signOut()

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this
        ) {  Log.d("logged ","out") }
    }
    private fun facebookSignOut(){
        mAuth.signOut()
        LoginManager.getInstance().logOut();

    }
    fun selectFrag(x:Int):FragmentManager{
        var fragmentManager:FragmentManager=supportFragmentManager
        when (x){
            0-> {homeFrag= HomeFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,homeFrag).commit()}
            1->{profileFrag= ProfileFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,profileFrag).commit()}
            2->{offersFrag= OffersFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,offersFrag).commit()}
            3->{chatFrag= ChatFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayout,chatFrag).commit()}
        }
        return fragmentManager
    }

}