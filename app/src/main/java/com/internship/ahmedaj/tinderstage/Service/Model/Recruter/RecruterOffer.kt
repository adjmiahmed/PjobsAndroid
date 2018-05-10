package com.internship.ahmedaj.tinderstage.Service.Model.Recruter

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class RecruterOffer(var _id: String,
var date_creation: Date) {
    override fun toString(): String {
        return "RecruterOffer(_id='$_id', date_creation=$date_creation)"
    }
}