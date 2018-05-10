package com.internship.ahmedaj.tinderstage.Service.Model.Offer

import java.util.*

/**
 * Created by ahmed aj on 07/05/2018.
 */
class CandInOffer (var _id:String,var date_application:Date,var etat_candidate:String){
    override fun toString(): String {
        return "CandInOffer(_id='$_id', date_application=$date_application, etat_candidate='$etat_candidate')"
    }
}