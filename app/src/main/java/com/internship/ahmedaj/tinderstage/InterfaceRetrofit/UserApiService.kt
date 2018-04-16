package com.internship.ahmedaj.tinderstage.InterfaceRetrofit

/**
 * Created by ahmed aj on 30/03/2018.
 */


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.internship.ahmedaj.tinderstage.Model.User
import com.internship.ahmedaj.tinderstage.Model.UserNoId

import okhttp3.OkHttpClient
import retrofit2.http.*


/**
 * Created by ahmed aj on 14/03/2018.
 */
interface UserApiService {

    @GET("GET/AllUsers")
    // fun getAllUsers(): Observable<User>
    fun getAllUsers(): Call<List<User>>

    @GET("GET/User/{id}")
    fun getUser(@Path("id") id:String ): Call<User>

    @GET("GET/Login/{username}")
    fun getUserbyUsername(@Path("username") username:String ): Call<User>

    @POST("ADD/USER")
    fun AddUser(@Body user: UserNoId): Call<User>

    @PUT("Update/USER/{id}")
    fun updateUser(@Path("id") id:String,@Body user:UserNoId ): Call<User>
    @DELETE("Delete/USER/{id}")
    fun deleteUser(@Path("id") id:String ): Call<User>



    companion object {
        fun create(): UserApiService {
            val okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://pjobs-androidwithnode.1d35.starter-us-east-1.openshiftapps.com/API/")
                    .client(okHttpClient)
                    .build()

            return retrofit.create(UserApiService::class.java)
        }
    }
}