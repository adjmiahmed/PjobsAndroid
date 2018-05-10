package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateExperience(var _id: String, var nom_entreprise: String, var titre_occuper: String, var tache_realiser: String, var date_entrer: Date, var date_sortie: Date) {
    override fun toString(): String {
        return "CandidateExperience(_id='$_id', nom_entreprise='$nom_entreprise', titre_occuper='$titre_occuper', tache_realiser='$tache_realiser', date_entrer='$date_entrer', date_sortie='$date_sortie')"
    }
}