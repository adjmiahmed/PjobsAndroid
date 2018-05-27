package com.internship.ahmedaj.tinderstage.Service.Repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * Created by ahmed aj on 24/05/2018.
 */
class NotificationRepository {
    companion object {
        var apiservice= APIService.create()
    }
fun sendToken(token:String):MutableLiveData<String>{
    var mutableLiveDataToken:MutableLiveData<String>
            mutableLiveDataToken= MutableLiveData()
    apiservice.sendToken(token).enqueue(object:retrofit2.Callback<String> {
        override fun onFailure(call: Call<String>?, t: Throwable?) {
         Log.e("error",t!!.message)
        }

        override fun onResponse(call: Call<String>?, response: Response<String>?) {
            mutableLiveDataToken.value=response!!.body()
            Log.e("reseponse",mutableLiveDataToken.value)
        }
    })
    return mutableLiveDataToken as MutableLiveData<String>
}

}