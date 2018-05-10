package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.facebook.login.LoginManager
import com.facebook.stetho.Stetho
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.*
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.*


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottombar.*
import kotlinx.android.synthetic.main.offreitem.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    //candidate profil init
    private lateinit var homeFrag: HomeFrag
    private lateinit var profileFrag: ProfileFrag
    private lateinit var offersFrag: OffersFrag
    private lateinit var chatFrag: ChatFrag
    //Recruter profil init
    private lateinit var homerectFrag: HomeRectFrag
    private lateinit var profilerectFrag: ProfileRectFrag
    private lateinit var offersrectFrag: OffersRectFrag
    private lateinit var chatrectFrag: ChatRectFrag


    private lateinit var toolbar: Toolbar
private lateinit var fmanager:FragmentManager
private lateinit var homeButton:ImageView
    private lateinit var profileButton: ImageView
    private lateinit var offersButton:ImageView
    private lateinit var chatButton:ImageView
    private lateinit var settingButton:ImageButton
    private lateinit  var mAuth: FirebaseAuth
private lateinit var tx_home:TextView
    private var size:Float=12.toFloat()
            private lateinit var tx_profile:TextView
    private lateinit var tx_offers:TextView
    private lateinit var tx_contact:TextView
    private lateinit var mGoogleSignInClient: GoogleSignInClient
  var x:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Stetho initialisation
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector( Stetho.defaultInspectorModulesProvider(this) )
        initializerBuilder.enableDumpapp( Stetho.defaultDumperPluginsProvider(this) )
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)

        //initialisation
        toolbar= app_bar as Toolbar
        setSupportActionBar(toolbar)
        homeButton=bt_accueil
        profileButton=bt_user
        offersButton=bt_offers
        chatButton=bt_chat
        settingButton=bt_setting
        tx_home=tx_homee
        tx_profile=tx_profilee
        tx_offers=findViewById(R.id.tx_offers)
        tx_contact=findViewById(R.id.tx_contact)

        //firebase auth
        mAuth = FirebaseAuth.getInstance()
        var gso   = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //google initialisation
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        settingButton.setOnClickListener{
            signOut()
            googleSignOut()
            facebookSignOut()
            startActivity(Intent(this, Login::class.java))
        }

        // getting condidate intent from login

        val intent = getIntent();
        val accounttype = intent.getStringExtra("accountType")
        if(accounttype!=null){

         if(accounttype.equals("Candidate")){


             selectFrag(x)
             homeButton.setOnClickListener {
                 x=0
                 tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_profile.textSize =size
                 tx_home.setTextColor(resources.getColor(R.color.colorbluebutton))
                 tx_home.textSize =size

                 tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_offers.textSize =size
                 tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_contact.textSize =size

                 homeButton.setImageResource(R.drawable.homeblue)
                 offersButton.setImageResource(R.drawable.casegrey)
                 profileButton.setImageResource(R.drawable.usergrey)
                 chatButton.setImageResource(R.drawable.ic_action_name)
                 Log.d("home","clicked")
                 selectFrag(x)}
             offersButton.setOnClickListener {
                 x=1
                 tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_profile.textSize =size
                 tx_offers.setTextColor(resources.getColor(R.color.colorbluebutton))
                 tx_offers.textSize =size

                 tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_home.textSize =size
                 offersButton.setImageResource(R.drawable.caseblue)
                 homeButton.setImageResource(R.drawable.acceuil)
                 profileButton.setImageResource(R.drawable.usergrey)
                 chatButton.setImageResource(R.drawable.ic_action_name)
                 tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_contact.textSize =size
                 Log.d("profile","clicked")
                 selectFrag(x)
             }
             profileButton.setOnClickListener {
                 x=2
                 tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_offers.textSize =size
                 tx_profile.setTextColor(resources.getColor(R.color.colorbluebutton))
                 tx_profile.textSize =size

                 tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_home.textSize =size
                 tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_contact.textSize =size

                 profileButton.setImageResource(R.drawable.profileblue)
                 offersButton.setImageResource(R.drawable.casegrey)
                 homeButton.setImageResource(R.drawable.acceuil)
                 chatButton.setImageResource(R.drawable.ic_action_name)
                 Log.d("offers","clicked")
                 selectFrag(x)
             }
             chatButton.setOnClickListener {

                 x=3
                 tx_contact.setTextColor(resources.getColor(R.color.colorbluebutton))
                 tx_contact.textSize =size

                 tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_offers.textSize =size

                 tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_profile.textSize =size
                 tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                 tx_home.textSize =size
                 offersButton.setImageResource(R.drawable.casegrey)
                 homeButton.setImageResource(R.drawable.acceuil)
                 profileButton.setImageResource(R.drawable.usergrey)
                 Log.d("chat","clicked")
                 chatButton.setImageResource(R.drawable.chatblue)
                 selectFrag(x)
             }

         }else
             if(accounttype.equals("Recruter")){
        x=4
                 selectFrag(x)
                 homeButton.setOnClickListener {
                     x=4
                     tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_profile.textSize =size
                     tx_home.setTextColor(resources.getColor(R.color.colorbluebutton))
                     tx_home.textSize =size

                     tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_offers.textSize =size
                     tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_contact.textSize =size

                     homeButton.setImageResource(R.drawable.homeblue)
                     offersButton.setImageResource(R.drawable.casegrey)
                     profileButton.setImageResource(R.drawable.usergrey)
                     chatButton.setImageResource(R.drawable.ic_action_name)
                     Log.d("home","clicked")
                     selectFrag(x)}
                 offersButton.setOnClickListener {
                     x=5
                     tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_profile.textSize =size
                     tx_offers.setTextColor(resources.getColor(R.color.colorbluebutton))
                     tx_offers.textSize =size

                     tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_home.textSize =size
                     offersButton.setImageResource(R.drawable.caseblue)
                     homeButton.setImageResource(R.drawable.acceuil)
                     profileButton.setImageResource(R.drawable.usergrey)
                     chatButton.setImageResource(R.drawable.ic_action_name)
                     tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_contact.textSize =size
                     Log.d("profile","clicked")
                     selectFrag(x)
                 }
                 profileButton.setOnClickListener {
                     x=6
                     tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_offers.textSize =size
                     tx_profile.setTextColor(resources.getColor(R.color.colorbluebutton))
                     tx_profile.textSize =size

                     tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_home.textSize =size
                     tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_contact.textSize =size

                     profileButton.setImageResource(R.drawable.profileblue)
                     offersButton.setImageResource(R.drawable.casegrey)
                     homeButton.setImageResource(R.drawable.acceuil)
                     chatButton.setImageResource(R.drawable.ic_action_name)
                     Log.d("offers","clicked")
                     selectFrag(x)
                 }
                 chatButton.setOnClickListener {

                     x=7
                     tx_contact.setTextColor(resources.getColor(R.color.colorbluebutton))
                     tx_contact.textSize =size

                     tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_offers.textSize =size

                     tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_profile.textSize =size
                     tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                     tx_home.textSize =size
                     offersButton.setImageResource(R.drawable.casegrey)
                     homeButton.setImageResource(R.drawable.acceuil)
                     profileButton.setImageResource(R.drawable.usergrey)
                     Log.d("chat","clicked")
                     chatButton.setImageResource(R.drawable.chatblue)
                     selectFrag(x)
                 }

             }

        }







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
            2->{profileFrag= ProfileFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,profileFrag).commit()}
            1->{offersFrag= OffersFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,offersFrag).commit()}
            3->{chatFrag= ChatFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayout,chatFrag).commit()}

            4->{homerectFrag= HomeRectFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,homerectFrag).commit()}
            6->{profilerectFrag= ProfileRectFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayout,profilerectFrag).commit()}
            5->{offersrectFrag= OffersRectFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayout,offersrectFrag).commit()}
            7->{chatrectFrag= ChatRectFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayout,chatrectFrag).commit()}






        }
        return fragmentManager
    }

}