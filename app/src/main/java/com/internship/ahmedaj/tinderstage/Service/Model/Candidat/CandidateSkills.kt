package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateSkills(var _id: String,
var nom_skill: String,
var niveau: String) {
    override fun toString(): String {
        return "CandidateSkills(_id='$_id', nom_skill='$nom_skill', niveau='$niveau')"
    }
}