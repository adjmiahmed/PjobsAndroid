package com.internship.ahmedaj.tinderstage.Service.Model.Offer

/**
 * Created by ahmed aj on 02/05/2018.
 */
class OfferSkill(var niveau: String,
var nom_skill: String,
var _id: String) {
    override fun toString(): String {
        return "OfferSkill(niveau='$niveau', nom_skill='$nom_skill', _id='$_id')"
    }
}