package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.Observer
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.facebook.login.LoginManager
import com.facebook.stetho.Stetho
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Repository.NotificationRepository
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nav_drowar.*
import kotlinx.android.synthetic.main.app_bar_nav_drowar.*
import kotlinx.android.synthetic.main.bottombar.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.security.auth.callback.Callback

class NavDrowarAct : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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


    private lateinit var fmanager: FragmentManager
    private lateinit var homeButton: ImageView
    private lateinit var profileButton: ImageView
    private lateinit var offersButton: ImageView
    private lateinit var chatButton: ImageView
    private lateinit var settingButton: ImageButton
    private lateinit var mAuth: FirebaseAuth
    private lateinit var tx_home: TextView
    private var size: Float = 12.toFloat()
    private lateinit var tx_profile: TextView
    private lateinit var tx_offers: TextView
    private lateinit var tx_contact: TextView
    private lateinit var mGoogleSignInClient: GoogleSignInClient
   private lateinit var allCandidates:AllCandidates
    private lateinit var editProfil: editProfil
    private lateinit var addOffreFrag: AddOffre
    private lateinit var calendarFrag: CalendarFrag
    lateinit var calandercand:CalandCand
    lateinit var editProfilCand:EditProfilCand
    lateinit var allOffreFrag:AllOffreFrag
    var x: Int = 0
    var accType:Int=0

lateinit var menu:Menu
    lateinit var allcand:MenuItem
    lateinit var addoffre:MenuItem
    lateinit var editprofil:MenuItem
    lateinit var calander:MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drowar)
       setSupportActionBar(toolbar)
Log.d("Token is:",FirebaseInstanceId.getInstance().token)
     /*   var not=FirebaseInstanceId.getInstance().token
        if(not!=null)
       sendRegistrationToServer(not)
       */
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        menu=nav_view.menu
        allcand=menu.findItem(R.id.nav_allCand)
        addoffre=menu.findItem(R.id.nav_addOffre)
        editprofil=menu.findItem(R.id.nav_editProfile)
        calander=menu.findItem(R.id.nav_calender)

        // /Stetho initialisation
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)

        //initialisation
 //       toolbar = app_bar as Toolbar
        homeButton = bt_accueil
        profileButton = bt_user
        offersButton = bt_offers
        chatButton = bt_chat
        //settingButton = bt_setting
        tx_home = tx_homee
        tx_profile = tx_profilee
        tx_offers = findViewById(R.id.tx_offers)
        tx_contact = findViewById(R.id.tx_contact)

        //firebase auth
        mAuth = FirebaseAuth.getInstance()
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //google initialisation
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
/*
        settingButton.setOnClickListener {
            signOut()
            googleSignOut()
            facebookSignOut()
            startActivity(Intent(this, Login::class.java))
        }
*/
        // getting condidate intent from login

        val intent = getIntent();
        val accounttype = intent.getStringExtra("accountType")
        if (accounttype != null) {

            if (accounttype.equals("Candidate")) {
                accType=1
                allcand.title="All Offers"
                addoffre.isVisible=false

                selectFrag(x)
                homeButton.setOnClickListener {
                    x = 0
                    tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_profile.textSize = size
                    tx_home.setTextColor(resources.getColor(R.color.colorbluebutton))
                    tx_home.textSize = size

                    tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_offers.textSize = size
                    tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_contact.textSize = size

                    homeButton.setImageResource(R.drawable.homeblue)
                    offersButton.setImageResource(R.drawable.casegrey)
                    profileButton.setImageResource(R.drawable.usergrey)
                    chatButton.setImageResource(R.drawable.ic_action_name)
                    Log.d("home", "clicked")
                    selectFrag(x)
                }
                offersButton.setOnClickListener {
                    x = 1
                    tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_profile.textSize = size
                    tx_offers.setTextColor(resources.getColor(R.color.colorbluebutton))
                    tx_offers.textSize = size

                    tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_home.textSize = size
                    offersButton.setImageResource(R.drawable.caseblue)
                    homeButton.setImageResource(R.drawable.acceuil)
                    profileButton.setImageResource(R.drawable.usergrey)
                    chatButton.setImageResource(R.drawable.ic_action_name)
                    tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_contact.textSize = size
                    Log.d("profile", "clicked")
                    selectFrag(x)
                }
                profileButton.setOnClickListener {
                    x = 2
                    tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_offers.textSize = size
                    tx_profile.setTextColor(resources.getColor(R.color.colorbluebutton))
                    tx_profile.textSize = size

                    tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_home.textSize = size
                    tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_contact.textSize = size

                    profileButton.setImageResource(R.drawable.profileblue)
                    offersButton.setImageResource(R.drawable.casegrey)
                    homeButton.setImageResource(R.drawable.acceuil)
                    chatButton.setImageResource(R.drawable.ic_action_name)
                    Log.d("offers", "clicked")
                    selectFrag(x)
                }
                chatButton.setOnClickListener {

                    x = 3
                    tx_contact.setTextColor(resources.getColor(R.color.colorbluebutton))
                    tx_contact.textSize = size

                    tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_offers.textSize = size

                    tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_profile.textSize = size
                    tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                    tx_home.textSize = size
                    offersButton.setImageResource(R.drawable.casegrey)
                    homeButton.setImageResource(R.drawable.acceuil)
                    profileButton.setImageResource(R.drawable.usergrey)
                    Log.d("chat", "clicked")
                    chatButton.setImageResource(R.drawable.chatblue)
                    selectFrag(x)
                }

            } else
                if (accounttype.equals("Recruter")) {
                    x = 4
                    selectFrag(x)
                    accType=2
                    homeButton.setOnClickListener {
                        x = 4
                        tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_profile.textSize = size
                        tx_home.setTextColor(resources.getColor(R.color.colorbluebutton))
                        tx_home.textSize = size

                        tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_offers.textSize = size
                        tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_contact.textSize = size

                        homeButton.setImageResource(R.drawable.homeblue)
                        offersButton.setImageResource(R.drawable.casegrey)
                        profileButton.setImageResource(R.drawable.usergrey)
                        chatButton.setImageResource(R.drawable.ic_action_name)
                        Log.d("home", "clicked")
                        selectFrag(x)
                    }
                    offersButton.setOnClickListener {
                        x = 5
                        tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_profile.textSize = size
                        tx_offers.setTextColor(resources.getColor(R.color.colorbluebutton))
                        tx_offers.textSize = size

                        tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_home.textSize = size
                        offersButton.setImageResource(R.drawable.caseblue)
                        homeButton.setImageResource(R.drawable.acceuil)
                        profileButton.setImageResource(R.drawable.usergrey)
                        chatButton.setImageResource(R.drawable.ic_action_name)
                        tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_contact.textSize = size
                        Log.d("profile", "clicked")
                        selectFrag(x)
                    }
                    profileButton.setOnClickListener {
                        x = 6
                        tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_offers.textSize = size
                        tx_profile.setTextColor(resources.getColor(R.color.colorbluebutton))
                        tx_profile.textSize = size

                        tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_home.textSize = size
                        tx_contact.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_contact.textSize = size

                        profileButton.setImageResource(R.drawable.profileblue)
                        offersButton.setImageResource(R.drawable.casegrey)
                        homeButton.setImageResource(R.drawable.acceuil)
                        chatButton.setImageResource(R.drawable.ic_action_name)
                        Log.d("offers", "clicked")
                        selectFrag(x)
                    }
                    chatButton.setOnClickListener {

                        x = 7
                        tx_contact.setTextColor(resources.getColor(R.color.colorbluebutton))
                        tx_contact.textSize = size

                        tx_offers.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_offers.textSize = size

                        tx_profile.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_profile.textSize = size
                        tx_home.setTextColor(resources.getColor(R.color.colorbackground))
                        tx_home.textSize = size
                        offersButton.setImageResource(R.drawable.casegrey)
                        homeButton.setImageResource(R.drawable.acceuil)
                        profileButton.setImageResource(R.drawable.usergrey)
                        Log.d("chat", "clicked")
                        chatButton.setImageResource(R.drawable.chatblue)
                        selectFrag(x)
                    }

                }


        }

    }


        override fun onBackPressed() {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.nav_drowar, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            when (item.itemId) {
                R.id.action_settings -> {
                    Log.d("option item slected", "aaaaaaaaaaaaaaaaaaaaa")
                    return true
                }
                R.id.action_disconnect->{signOut()
                    googleSignOut()
                    facebookSignOut()
                    startActivity(Intent(this, Login::class.java))
                return true}
                else -> return super.onOptionsItemSelected(item)
            }
        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            // Handle navigation view item clicks here.
            var fragmentManager: FragmentManager = supportFragmentManager

            if(accType===1){when (item.itemId) {
               R.id.nav_allCand -> {
                   // Handle the camera action
                   Log.d("allcand pressed", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                   allCandidates= AllCandidates()
                   fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, allCandidates).commit()
               }
               R.id.nav_editProfile -> {

                     editProfil= editProfil()
                   fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, editProfil).commit()
                   Log.d("edit profile Pressed", "aaaaaaaaaaaaaa")
               }

               R.id.nav_calender -> {
              Log.d("calender pressed", "bbbbbbbbbb")
                 addOffreFrag= AddOffre()
                   fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, addOffreFrag).commit()
                /*   calendarFrag= CalendarFrag()
                   fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, calendarFrag).commit()
               */
               }
               R.id.nav_desc -> {
                   Log.d("desc pressed", "aaaaaaaaaaaaaa")
                   addOffreFrag= AddOffre()
                   fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, addOffreFrag).commit()
               }
               R.id.nav_copyrigth -> {
                   Log.d("copyrigth pressed", "aaaaaaaaaaaaaaaaaaaaaaaa")
               }
           }
           }
            else if(accType===2){when (item.itemId) {
                R.id.nav_allCand -> {
                    // Handle the camera action
                    Log.d("allcand pressed", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                    allOffreFrag= AllOffreFrag()
                    fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, allOffreFrag).commit()
                }
                R.id.nav_editProfile -> {

                    editProfilCand= EditProfilCand()
                    fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, editProfilCand).commit()
                    Log.d("edit profile Pressed", "aaaaaaaaaaaaaa")
                }
                R.id.nav_calender -> {
                    Log.d("calender pressed", "bbbbbbbbbbbb")
                    calandercand= CalandCand()
                    fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, calandercand).commit()
                }
                R.id.nav_addOffre -> {
                    Log.d("add offre pressed", "cccccccccc")
                    addOffreFrag= AddOffre()
                    fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, addOffreFrag).commit()
                }
               R.id.nav_desc -> {
                   Log.d("desc pressed", "aaaaaaaaaaa")
               }
               R.id.nav_copyrigth -> {
                   Log.d("copyrigth pressed", "aaaaaaaaaaaa")
               }
           }
           }
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }

    fun sendRegistrationToServer(token:String){
        val notifcationrep= NotificationRepository()
      notifcationrep.sendToken(token).observe(this@NavDrowarAct ,object: Observer<String> {
            override fun onChanged(t: String?) {
                Log.d("msg rept token",t)
            }
        })

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
        ) { Log.d("logged ", "out") }
    }

    private fun facebookSignOut() {
        mAuth.signOut()
        LoginManager.getInstance().logOut();

    }

    fun selectFrag(x: Int): FragmentManager {
        var fragmentManager: FragmentManager = supportFragmentManager
        when (x) {
            0 -> {
                homeFrag = HomeFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, homeFrag).commit()
            }
            2 -> {
                profileFrag = ProfileFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, profileFrag).commit()
            }
            1 -> {
                offersFrag = OffersFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, offersFrag).commit()
            }
            3 -> {
                chatFrag = ChatFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, chatFrag).commit()
            }

            4 -> {
                homerectFrag = HomeRectFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, homerectFrag).commit()
            }
            6 -> {
                profilerectFrag = ProfileRectFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, profilerectFrag).commit()
            }
            5 -> {
                offersrectFrag = OffersRectFrag()

                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, offersrectFrag).commit()
            }
            7 -> {
                chatrectFrag = ChatRectFrag()
                fragmentManager.beginTransaction().replace(R.id.mainlayoutApp, chatrectFrag).commit()
            }


        }
        return fragmentManager
    }
}
