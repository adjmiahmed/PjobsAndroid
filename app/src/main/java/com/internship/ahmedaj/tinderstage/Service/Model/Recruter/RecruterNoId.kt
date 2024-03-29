package com.internship.ahmedaj.tinderstage.Service.Model.Recruter

import java.util.*

/**
 * Created by ahmed aj on 17/05/2018.
 */
class RecruterNoId(var nom: String,
                   var email: String,
                   var passwd: String,
                   var dateNaissance: Date,
                   var numtel: Int,
                   var adress: String,
                   var url_img: String,
                   var genre: String,
                   var offers:List<RecruterOffer>,
                   var entreprise: RecruterEntreprise) {

    override fun toString(): String {
        return "RecruterNoId(nom='$nom', email='$email', passwd='$passwd', dateNaissance=$dateNaissance, numtel=$numtel, adress='$adress', url_img='$url_img', genre='$genre', offers=$offers, entreprise=$entreprise)"
    }
}