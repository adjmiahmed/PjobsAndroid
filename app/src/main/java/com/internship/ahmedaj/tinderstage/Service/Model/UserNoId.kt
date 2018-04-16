package com.internship.ahmedaj.tinderstage.Service.Model

/**
 * Created by ahmed aj on 30/03/2018.
 */
import com.google.gson.annotations.SerializedName
class UserNoId(@SerializedName("username") var username:String, @SerializedName("passwd") var passwd:String, @SerializedName("email") var email:String, @SerializedName("accountType") var accountType:String ) {

}