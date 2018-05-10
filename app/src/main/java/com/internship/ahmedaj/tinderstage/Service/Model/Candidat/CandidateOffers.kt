package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateOffers(var date_application: String, var statu: String, var _id: String) {
    override fun toString(): String {
        return "CandidateOffers(date_application='$date_application', statu='$statu', _id='$_id')"
    }
}