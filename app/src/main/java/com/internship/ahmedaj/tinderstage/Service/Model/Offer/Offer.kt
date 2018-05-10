package com.internship.ahmedaj.tinderstage.Service.Model.Offer

import java.util.*

/**
 * Created by ahmed aj on 02/05/2018.
 */
class Offer(var _id: String,
             var titre_job: String,
             var description: String,
             var salaire: Int,
             var date_offre: Date,
             var background: String,
             var niveau_etude: String,
             var status: String,
             var id_recruteur: String,
             var nom_entreprise: String,
             var candidates:List<OfferCandiates>,
             var language:List<OfferLanguage>,
             var skills:List<OfferSkill>){
    override fun toString(): String {
        return "Offer(_id='$_id', titre_job='$titre_job', description='$description', salaire=$salaire, date_offre=$date_offre, background='$background', niveau_etude='$niveau_etude', status='$status', id_recruteur='$id_recruteur', nom_entreprise='$nom_entreprise', candidates=$candidates, language=$language, skills=$skills)"
    }
}