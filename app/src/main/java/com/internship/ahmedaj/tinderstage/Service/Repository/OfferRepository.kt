package com.internship.ahmedaj.tinderstage.Service.Repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ahmed aj on 02/05/2018.
 */
class OfferRepository {
companion object {
    var apiservice= APIService.create()
}


    fun getAllOffersLiveData(): MutableLiveData<List<Offer>> {
        var mutableLiveData: MutableLiveData<List<Offer>> = MutableLiveData()
        Log.d("hi its all cond retrof","")
        apiservice.getAllOffers().enqueue(object:Callback<List<Offer>>{
            override fun onFailure(call: Call<List<Offer>>?, t: Throwable?) {
                Log.d("failer on offer list",t!!.message)
                Log.d("failer on offer list", t.localizedMessage)

            }

            override fun onResponse(call: Call<List<Offer>>?, response: Response<List<Offer>>?) {
           Log.d("on response list offer","")
           mutableLiveData.value=response!!.body()

            }
        })



        return  mutableLiveData
    }

    fun addCandToOffre(id:String,body: CandInOffer):MutableLiveData<Offer>{
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()
        apiservice.addCandToOffre(id,body).enqueue(object:Callback<Offer>{
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on adding  ","cand offer"+t!!.message)
            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {
                Log.d("success on adding  ","cand offer"+response!!.body().toString())
               mutableLiveData.value=response!!.body()

                 }

        })
        return mutableLiveData
    }
    fun ChangeOfferCandetat(id:String, body: CandInOffer):MutableLiveData<Offer>{
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()
        apiservice.ChangeOfferCandetat(id,body).enqueue(object:Callback<Offer>{
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on changing  ","offercand etat"+t!!.message)
            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {
                Log.d("success on changing  ","offercand etat"+response!!.body().toString())
                mutableLiveData.value=response!!.body()

            }


        })

        return mutableLiveData
    }

    fun getOfferCand(id:String):MutableLiveData<List<Candidate>>{
        var mutableLiveData: MutableLiveData<List<Candidate>> = MutableLiveData()

        apiservice.getOfferCand(id).enqueue(object:Callback<List<Candidate>>{
            override fun onFailure(call: Call<List<Candidate>>?, t: Throwable?) {
                Log.d("failer on offer  ","cand"+t!!.message)

            }

            override fun onResponse(call: Call<List<Candidate>>?, response: Response<List<Candidate>>?) {
                Log.d("Sucess on offer  ","cand")
                mutableLiveData.value=response!!.body()

            }

        })
        return mutableLiveData
    }



}