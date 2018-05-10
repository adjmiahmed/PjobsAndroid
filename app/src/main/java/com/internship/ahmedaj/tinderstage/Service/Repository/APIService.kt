package com.internship.ahmedaj.tinderstage.Service.Repository

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import io.reactivex.internal.operators.observable.ObservableFromFuture


/**
 * Created by ahmed aj on 17/04/2018.
 */
interface APIService {

    @GET("GET/AllCandidats")
    fun getAllCandidates(): Call<List<Candidate>>
    @GET("GET/AllOffers")
    fun getAllOffers(): Call<List<Offer>>
    @GET("GET/AllRecruteur")
    fun getAllRecruters(): Call<List<Recruter>>
    @GET("Get/CandByEmail/{email}")
    fun getCandbyEmail(@Path("email") email:String):Call<Candidate>
    @GET("Get/RectByEmail/{email}")
    fun getRectbyEmail(@Path("email") email:String):Call<Recruter>
    @GET("Get/RectOffers/{id}")
    fun getRectOffers(@Path("id") id:String):Call<List<Offer>>
    @GET("Get/offersCand/{id}")
    fun getOfferCand(@Path("id") id:String):Call<List<Candidate>>
    @GET("Get/CandidatOffers/{id}")
    fun getCandidatOffers(@Path("id") id:String):Call<List<Offer>>


    @PUT("AddCandToOffre/{id}")
    fun addCandToOffre(@Path("id") id:String,@Body candInoffer: CandInOffer): Call<Offer>
    @PUT("AddOfferToCandiat/{id}")
    fun addOffreToCand(@Path("id") id:String,@Body candInoffer: OfferInCand): Call<Candidate>
    @PUT("ChangeCandOfferStatus/{id}")
    fun ChangeCandOfferStatus(@Path("id") id:String,@Body candInoffer: OfferInCand): Call<Candidate>
    @PUT("ChangeOfferCandetat/{id}")
    fun ChangeOfferCandetat(@Path("id") id:String,@Body candInoffer: CandInOffer): Call<Offer>



    companion object {
        fun create(): APIService {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            val okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://pjobs-androidwithnode.1d35.starter-us-east-1.openshiftapps.com/API/")
                    .client(okHttpClient)
                    .build()

            return retrofit.create(APIService::class.java)
        }
    }

}
