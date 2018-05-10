package com.internship.ahmedaj.tinderstage.Service.Model.Recruter

/**
 * Created by ahmed aj on 02/05/2018.
 */
class RecruterEntreprise(var nom_entreprise: String,
var num_entreprise: Int,
var adress_entreprise: String,
var description: String,
var email_entreprise: String,
var logo: String,
var siteweb: String) {
    override fun toString(): String {
        return "RecruterEntreprise(nom_entreprise='$nom_entreprise', num_entreprise=$num_entreprise, adress_entreprise='$adress_entreprise', description='$description', email_entreprise='$email_entreprise', logo='$logo', siteweb='$siteweb')"
    }
}