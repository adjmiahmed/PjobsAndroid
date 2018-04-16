package com.internship.ahmedaj.tinderstage.Service.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by ahmed aj on 23/03/2018.
 */
class Profile {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("age")
    @Expose
    var age: Int? = null

    @SerializedName("location")
    @Expose
    var location: String? = null
}
