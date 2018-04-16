package com.internship.ahmedaj.tinderstage.Model

/**
 * Created by ahmed aj on 30/03/2018.
 */
import com.google.gson.annotations.SerializedName
class User(  @SerializedName("_id") var _id:String, @SerializedName("username") var username:String,@SerializedName("passwd") var passwd:String,@SerializedName("email") var email:String,@SerializedName("accountType") var accountType:String ) {

}
