package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ahmed aj on 02/05/2018.
 */
class Candidate(
        var _id: String,
        var nom: String,
        var email: String,
        var passwd: String,
        var dateNaissance: Date,
        var numtel: Int,
        var adress: String,
        var url_img: String,
        var genre: String,
        var MyOffers:ArrayList<CandidateOffers>,
        var experience_professionel:ArrayList<CandidateExperience>,
        var skills:ArrayList<CandidateSkills>,
        var education:ArrayList<CandidateEducation>,
        var projet:ArrayList<CandidateProjects>,
        var language:ArrayList<CandidateLanguage>
) {
    override fun toString(): String {
        return "Candidate(_id='$_id', nom='$nom', email='$email', passwd='$passwd', dateNaissance=$dateNaissance, numtel=$numtel, adress='$adress', url_img='$url_img', genre='$genre', MyOffers=$MyOffers, experience_professionel=$experience_professionel, skills=$skills, education=$education, projet=$projet, language=$language)"
    }
}