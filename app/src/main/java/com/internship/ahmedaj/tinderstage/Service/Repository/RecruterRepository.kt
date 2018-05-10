package com.internship.ahmedaj.tinderstage.Service.Repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ahmed aj on 02/05/2018.
 */
class RecruterRepository {

    companion object {
        var apiservice= APIService.create()
    }


    fun getAllRecrutersLiveData(): MutableLiveData<List<Recruter>> {
        var mutableLiveData: MutableLiveData<List<Recruter>> = MutableLiveData()
        Log.d("hi its all cond retrof","")
        apiservice.getAllRecruters().enqueue(object:Callback<List<Recruter>>{
            override fun onFailure(call: Call<List<Recruter>>?, t: Throwable?) {
                Log.d("failer on offer list",t!!.message)
                Log.d("failer on offer list", t.localizedMessage)

            }

            override fun onResponse(call: Call<List<Recruter>>?, response: Response<List<Recruter>>?) {
                Log.d("on response" ,"list Recruter")
                mutableLiveData.value=response!!.body()
            }

        })



        return  mutableLiveData
    }


    fun getRecruterByEmail(email:String):MutableLiveData<Recruter>
    {
        var mutableLiveData: MutableLiveData <Recruter> = MutableLiveData()

        apiservice.getRectbyEmail(email).enqueue(object:Callback<Recruter>{
            override fun onFailure(call: Call<Recruter>?, t: Throwable?) {
                Log.d("error ","in on get rect by email")
            }

            override fun onResponse(call: Call<Recruter>?, response: Response<Recruter>?) {

mutableLiveData.value=response!!.body()

            }
        })

        return mutableLiveData
    }


fun getRectOffers(id:String):MutableLiveData<List<Offer>>{
    var mutableLiveData:MutableLiveData <List<Offer>>
    mutableLiveData=MutableLiveData()

    apiservice.getRectOffers(id).enqueue(object:Callback<List<Offer>>{
        override fun onResponse(call: Call<List<Offer>>?, response: Response<List<Offer>>?) {

        mutableLiveData.value=response!!.body()
        }

        override fun onFailure(call: Call<List<Offer>>?, t: Throwable?) {
        Log.d("failuer in get ","Recru offers")
        }


    })


    return mutableLiveData

}

}