package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateEducation(var _id: String, var nom_etablissement: String, var date_entrer: Date, var diplome: String) {
    override fun toString(): String {
        return "CandidateEducation(_id='$_id', nom_etablissement='$nom_etablissement', date_entrer=$date_entrer, diplome='$diplome')"
    }
}
