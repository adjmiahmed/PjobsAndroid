package com.internship.ahmedaj.tinderstage.Service.Model.Offer

/**
 * Created by ahmed aj on 02/05/2018.
 */
class OfferLanguage(var niveau: String,
var nom_langue: String,
var _id: String) {
    override fun toString(): String {
        return "OfferLanguage(niveau='$niveau', nom_langue='$nom_langue', _id='$_id')"
    }
}