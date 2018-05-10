package com.internship.ahmedaj.tinderstage.Service.Model.Offer

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class OfferCandiates(var date_application: Date,
var etat_candidate: String,
var _id: String) {
    override fun toString(): String {
        return "OfferCandiates(date_application=$date_application, etat_candidate='$etat_candidate', _id='$_id')"
    }
}