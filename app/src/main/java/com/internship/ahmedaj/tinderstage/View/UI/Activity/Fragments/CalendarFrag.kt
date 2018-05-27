package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.iid.FirebaseInstanceId

import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.User
import com.internship.ahmedaj.tinderstage.Service.Repository.APIService
import kotlinx.android.synthetic.main.fragment_calendar.*
import okhttp3.RequestBody
import okhttp3.ResponseBody


/**
 * A simple [Fragment] subclass.
 */
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CalendarFrag : Fragment() {
    lateinit var bt:Button
lateinit var tx_token:TextView
    lateinit var tx_user:TextView
    companion object {
        var apiservice= APIService.create()
    }
lateinit var mAuth:FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var view =inflater.inflate(R.layout.fragment_calendar, container, false)

        var x=FirebaseInstanceId.getInstance().getToken()

bt=view.findViewById(R.id.secureUrl)
     tx_token=view.findViewById(R.id.tx_Token)
        tx_user=view.findViewById(R.id.tx_user1)
        bt.setOnClickListener {
            Log.d("66666666666666", "aaaaaaaaaaaaaaa"+x)

            var  mUser = FirebaseAuth.getInstance().currentUser
            mUser!!.getIdToken(true).addOnCompleteListener {task->
                if (task.isSuccessful()) {
                    val idToken = task.getResult().getToken()
                    // Send token to your backend via HTTPS
                    // ...
                    tx_token.text=idToken.toString()
                  var x=  testsecure(idToken.toString())


                } else {
                    // Handle error -> task.getException();
                }
            }


        }
       /*     mAuth.currentUser!!.getIdToken(true).addOnCompleteListener(object: OnCompleteListener<GetTokenResult> {
                override fun onComplete(task: Task<GetTokenResult>) {
                    if (task.isSuccessful()) {
                        var idToken= task.getResult().getToken();
                        getAlluser(idToken.toString())
                    } else {
                        // Handle error -> task.getException();
                        Log.d("error in getting users","")

                    } }
            })

        }
*/
        /*  bt.setOnClickListener {
            Log.d("in bt click","")
            mAuth.currentUser!!.getIdToken(true).addOnCompleteListener(object: OnCompleteListener<GetTokenResult> {
                override fun onComplete(task: Task<GetTokenResult>) {
                    if (task.isSuccessful()) {
                        var idToken= task.getResult().getToken();
                        getAlluser(idToken.toString())
                    } else {
                        // Handle error -> task.getException();
                        Log.d("error in getting users","")

                    } }
            })
        }
*/


        return view
    }

fun testsecure(token:String): ResponseBody?
{ var x: ResponseBody? =null
    apiservice.testsecure(token).enqueue(object :Callback<ResponseBody>{
        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            Log.d("error",""+t.toString())

        }

        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            if (response != null) {
            x= response.body()
                Log.d("response",""+x+"")

            }

        }


    })
return x
}
    fun getAlluser(token:String):List<User>
    {var list:List<User>
        list=ArrayList<User>()
        apiservice.getAllUsers(token).enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                Log.d("error",""+t.toString())
            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                if (response != null) {
                    list= response.body()!!

                    tx_token.text=FirebaseInstanceId.getInstance().token.toString()
                  //  tx_user.text=list[0].username
                }
                Log.d("response null","")


            }
        })
        return list
    }




}// Required empty public constructor
