package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 20/05/2018.
 */
class CandidateEdcationNoId(var nom_etablissement: String, var date_entrer: Date, var diplome: String) {
    override fun toString(): String {
        return "CandidateEdcationNoId(nom_etablissement='$nom_etablissement', date_entrer=$date_entrer, diplome='$diplome')"
    }
}