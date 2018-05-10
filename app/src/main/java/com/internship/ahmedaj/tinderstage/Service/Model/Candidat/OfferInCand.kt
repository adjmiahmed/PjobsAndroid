package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 08/05/2018.
 */
class OfferInCand(var _id:String,var date_application:Date,var statu:String) {

    override fun toString(): String {
        return "OfferInCand(_id='$_id', date_application=$date_application, statu='$statu')"
    }
}