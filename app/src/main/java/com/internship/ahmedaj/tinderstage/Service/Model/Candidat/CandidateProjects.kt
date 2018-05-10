package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateProjects(var _id: String, var nom_project: String, var date_debut: Date, var date_fin: Date, var description: String, var tache_realiser: String) {
    override fun toString(): String {
        return "CandidateProjects(_id='$_id', nom_project='$nom_project', date_debut=$date_debut, date_fin=$date_fin, description='$description', tache_realiser='$tache_realiser')"
    }
}