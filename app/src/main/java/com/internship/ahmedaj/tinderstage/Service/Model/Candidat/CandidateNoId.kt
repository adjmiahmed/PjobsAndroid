package com.internship.ahmedaj.tinderstage.Service.Model.Candidat

import java.util.*

/**
 * Created by ahmed aj on 17/05/2018.
 */
class CandidateNoId(
                    var nom: String,
                    var email: String,
                    var passwd: String,
                    var dateNaissance: Date,
                    var numtel: Int,
                    var adress: String,
                    var url_img: String,
                    var genre: String,
                    var MyOffers:ArrayList<CandidateOffers>,
                    var experience_professionel:ArrayList<CandidateExperienceNoId>,
                    var skills:ArrayList<CandidateSkillsNoId>,
                    var education:ArrayList<CandidateEdcationNoId>,
                    var projet:ArrayList<CandidateProjectsNoId>,
                    var language:ArrayList<CandidateLanguageNoId>
) {
    override fun toString(): String {
        return "CandidateNoId(nom='$nom', email='$email', passwd='$passwd', dateNaissance=$dateNaissance, numtel=$numtel, adress='$adress', url_img='$url_img', genre='$genre', MyOffers=$MyOffers, experience_professionel=$experience_professionel, skills=$skills, education=$education, projet=$projet, language=$language)"
    }
}