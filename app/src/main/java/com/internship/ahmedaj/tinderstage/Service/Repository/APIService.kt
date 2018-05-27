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
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.OfferNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.RecruterNoId
import com.internship.ahmedaj.tinderstage.Service.Model.User
import io.reactivex.internal.operators.observable.ObservableFromFuture
import okhttp3.ResponseBody


/**
 * Created by ahmed aj on 17/04/2018.
 */
interface APIService {
    @GET("Get/AllUsers")
    fun getAllUsers(@Header("Authorization") token:String):Call<List<User>>
    @GET("API/")
    fun testsecure(@Header("Authorization") token:String):Call<ResponseBody>

    @GET("API/GET/AllCandidats")
    fun getAllCandidates(): Call<List<Candidate>>
    @GET("API/GET/AllOffers")
    fun getAllOffers(): Call<List<Offer>>
    @GET("API/GET/AllRecruteur")
    fun getAllRecruters(): Call<List<Recruter>>
    @GET("API/Get/CandByEmail/{email}")
    fun getCandbyEmail(@Path("email") email:String):Call<Candidate>
    @GET("API/Get/RectByEmail/{email}")
    fun getRectbyEmail(@Path("email") email:String):Call<Recruter>
    @GET("API/Get/RectOffers/{id}")
    fun getRectOffers(@Path("id") id:String):Call<List<Offer>>
    @GET("API/Get/offersCand/{id}")
    fun getOfferCand(@Path("id") id:String):Call<List<Candidate>>
    @GET("API/Get/CandidatOffers/{id}")
    fun getCandidatOffers(@Path("id") id:String):Call<List<Offer>>
    @GET("API/Get/offerByRectID/{id}")
    fun getofferByRectID(@Path("id") id:String):Call<List<Offer>>
    @GET("API/Get/offer/{id}")
    fun getOfferById(@Path("id") id:String):Call<Offer>
    @GET("API/Get/Recruter/{id}")
    fun getRecruterById(@Path("id") id:String):Call<Recruter>


    @PUT("API/AddCandToOffre/{id}")
    fun addCandToOffre(@Path("id") id:String,@Body candInoffer: CandInOffer): Call<Offer>
    @PUT("API/AddOfferToCandiat/{id}")
    fun addOffreToCand(@Path("id") id:String,@Body candInoffer: OfferInCand): Call<Candidate>
    @PUT("API/ChangeCandOfferStatus/{id}")
    fun ChangeCandOfferStatus(@Path("id") id:String,@Body candInoffer: OfferInCand): Call<Candidate>
    @PUT("API/ChangeOfferCandetat/{id}")
    fun ChangeOfferCandetat(@Path("id") id:String,@Body candInoffer: CandInOffer): Call<Offer>

    //creation
    @POST("API/ADD/Candidat")
    fun CreateCandidate(@Body candidate:CandidateNoId):Call<Candidate>
    @POST("API/ADD/Recruter")
    fun CreateRecruter(@Body recruter:RecruterNoId):Call<Recruter>
    @POST("API/ADD/Offer")
    fun CreateOffer(@Body offer:OfferNoId):Call<Offer>

    //update
    @PUT("API/Update/Candidat/{id}")
    fun updateCandidate(@Path("id") id:String,@Body cand:CandidateNoId):Call<Candidate>
    @PUT("API/Update/Recruter/{id}")
    fun updateRecruter(@Path("id") id:String,@Body rect:RecruterNoId):Call<Recruter>
    @PUT("API/Update/offer/{id}")
    fun updateOffer(@Path("id") id:String,@Body offer:OfferNoId):Call<Offer>

    //token
    @GET("/API/Token")
    fun sendToken(@Header("Token") token: String):Call<String>




    companion object {
        fun create(): APIService {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            val okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://pjobs-androidwithnode.1d35.starter-us-east-1.openshiftapps.com/")
                    .client(okHttpClient)
                    .build()

            return retrofit.create(APIService::class.java)
        }
    }

}
