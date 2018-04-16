package com.internship.ahmedaj.tinderstage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton
import com.facebook.stetho.Stetho
import com.internship.ahmedaj.tinderstage.InterfaceRetrofit.UserApiService
import com.internship.ahmedaj.tinderstage.Model.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.R.attr.password
import android.widget.Button
import android.widget.ImageButton
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*

class Login : AppCompatActivity() {
//button et editText
private lateinit var bt:CircularProgressButton
    private lateinit var signup:Button
    private lateinit var tx_username: EditText
    private lateinit var tx_passwd:EditText
    private lateinit var bt_googlesignin:ImageButton
    private lateinit var bt_facebooksignin:LoginButton
    private var conn=true
    //object retrofit
    private  var respUser:User=User("","","","","")
    private val UserApiServe by lazy {
        UserApiService.create()
    }
    //firebase
    private lateinit  var mAuth: FirebaseAuth
   //google singin
    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001
    private lateinit var mGoogleSignInClient:GoogleSignInClient
//facebook signin
private val TAGFacebook = "FacebookLogin"
 private lateinit var mCallbackManager: CallbackManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        FacebookSdk.sdkInitialize(getApplicationContext())
        FacebookSdk.setApplicationId("1936579626374375")

        //initialisation du view
        tx_username=ed_email
        tx_passwd=ed_passwd
        bt=progressFix
        signup=bt_signup
        bt_googlesignin=bt_googleSignIn
        bt_facebooksignin=bt_facebookSignIn
        //firebase auth
        mAuth = FirebaseAuth.getInstance()
        // Configure Google Sign In
       var gso   = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//facebook conf
        mCallbackManager = CallbackManager.Factory.create();
        bt_facebooksignin.setReadPermissions("email", "public_profile");
        bt_facebooksignin.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {

                Log.d(TAG, "facebook:onSuccess:" + result)
                Toast.makeText(applicationContext, "Authentication Sucess",
                        Toast.LENGTH_SHORT).show()
                handleFacebookAccessToken(result!!.getAccessToken());
            }

            override fun onCancel() {

                Log.d(TAG, "facebook:onCancel");

                // [START_EXCLUDE]
                updateUI(null);
            }

            override fun onError(error: FacebookException?) {

                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(applicationContext, "Authentication Error",
                        Toast.LENGTH_SHORT).show()
                // [START_EXCLUDE]
                updateUI(null);
            }
        })
        //initialisation of stetho
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector( Stetho.defaultInspectorModulesProvider(this) )
        initializerBuilder.enableDumpapp( Stetho.defaultDumperPluginsProvider(this) )
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)

        //button signup action
        signup.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
        }
        if((tx_username.text.toString()!="")&&(tx_passwd.text.toString()!=""))
        //button login action
        bt.setOnClickListener{

            mAuth.signInWithEmailAndPassword(tx_username.text.toString(), tx_passwd.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success")
                            Toast.makeText(this, "Authentication sucess",
                                    Toast.LENGTH_SHORT).show()
                            val user = mAuth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //startActivity(Intent(this, Login::class.java))
                         conn=false
                            updateUI(null)
                        }

                        // ...
                    }
            animateButtonAndRevert(progressFix,
                    ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark),BitmapFactory.decodeResource(resources, R.drawable.checkmark) )

        }
        bt_googlesignin.setOnClickListener{
            googleSignIn()
        }
        }


    private fun handleFacebookAccessToken(token: AccessToken) {

        Log.d(TAG, "handleFacebookAccessToken:" + token.toString())
        var credential:AuthCredential  = FacebookAuthProvider.getCredential(token.getToken())
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, object:OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                          if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                              var user:FirebaseUser  = mAuth.getCurrentUser()!!;
                              updateUI(user);

                          } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(applicationContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
         // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            var task:Task<GoogleSignInAccount>  = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                var account :GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e:ApiException ) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }



    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId())
       var credential: AuthCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, object:OnCompleteListener<AuthResult> {
                    override fun onComplete(@NonNull  task:Task<AuthResult>) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            var user:FirebaseUser  = mAuth.getCurrentUser()!!;
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Log.w("", "signInWithEmail:failure", task.exception)
                            Toast.makeText(applicationContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null);
                        }

                                          }
                });
    }


    // [START signin]
    private fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun googleSignOut() {
        // Firebase sign out
        mAuth.signOut()

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this
        ) { updateUI(null) }
    }

    private fun revokeAccess() {
        // Firebase sign out
        mAuth.signOut()

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this
        ) { updateUI(null) }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

     fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser==null){
            Toast.makeText(this, "No user connected",
                    Toast.LENGTH_SHORT).show()
            mAuth.signOut()
        }else{

            Toast.makeText(this, "current user is :"+currentUser.email,
                    Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
        //signout methode
        private fun signOut() {
            mAuth.signOut()
            updateUI(null)
            }
    // appel au web service method Get recuperation des username et passwd

    private fun getUserbyUsername(){

        var user: Call<User>

        //while(tx_username.text==null){}
        user= UserApiServe.getUserbyUsername(tx_username.text.toString())
        user.enqueue(object: Callback<User> {

            override fun onFailure(call: Call<User>?, t: Throwable?)
            {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("","error in onfailure")
                Toast.makeText(applicationContext,
                        "plz enter a valid username", Toast.LENGTH_SHORT).show();

            }

            override fun onResponse(call: Call<User>?, response: Response<User>?)
            {
                Log.d("","onSucess Call")
                Log.d("our response egale:",response?.body()!!.passwd.toString())
                Toast.makeText(applicationContext, response?.body()!!.passwd.toString()+response?.body()!!.username.toString(), Toast.LENGTH_SHORT).show();
                if(tx_passwd.equals(response?.body()!!.passwd.toString())) {
                    respUser = User(response?.body()!!._id.toString(), response?.body()!!.username.toString(), response?.body()!!.passwd.toString(), response?.body()!!.email.toString(), response?.body()!!.accountType.toString())
                }else {
                    respUser = User("", "", "", "", "")
                    Toast.makeText(applicationContext,"plz enter a valid password", Toast.LENGTH_SHORT).show();

                }

            }
        })


    }

    //annimation methode
     private fun animateButtonAndRevert(circularProgressButton: CircularProgressButton, fillColor: Int, bitmap: Bitmap) {
        val handler = Handler()

        val runnable = {
            circularProgressButton.doneLoadingAnimation(
                    fillColor,
                    bitmap)
        }

        val changeActivity = {
            if(conn)
            startActivity(Intent(this, MainActivity::class.java))
            else
                startActivity(Intent(this, Login::class.java))
            finish()
        }

        circularProgressButton.revertAnimation()

        circularProgressButton.startAnimation()
        handler.postDelayed(runnable, 1000)
        handler.postDelayed(changeActivity, 2000)
        handler.postDelayed(changeActivity, 2100)
    }

}
