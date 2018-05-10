package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateLanguage (var _id:String, var nom_langue:String, var niveau:String){
    override fun toString(): String {
        return "CandidateLanguage(_id='$_id', nom_langue='$nom_langue', niveau='$niveau')"
    }
}