package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandItemAcceptedList
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.AllCandidates
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.HomeRectFrag
import com.internship.ahmedaj.tinderstage.ViewModel.CandidateCardViewModel
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by ahmed aj on 15/05/2018.
 */
@Layout(R.layout.candidatcard)

class CandAllCards(private val allCandidates: AllCandidates, private val mContext: Context, private val mSwipeView: SwipePlaceHolderView, var t: Candidate, val id:String) {

    var candidateViewModel: CandidateCardViewModel
    val eta_cond_Accepted:String="Accepted"
    val eta_cond_Reject:String="Rejected"
    val date_application= Date()
    val acceptedOfferInCand= OfferInCand(id,date_application,eta_cond_Accepted)

    val candofferetaat= CandInOffer(t._id,date_application,eta_cond_Accepted)
    val candofferetaatRejected= CandInOffer(t._id,date_application,eta_cond_Reject)

    val OfferInCandReject= OfferInCand(id,date_application,eta_cond_Reject)
    var gson= Gson()

    var sharedPrefCand: SharedPreferences =mContext.getSharedPreferences("CandPref",Context.MODE_PRIVATE)
    lateinit  var idCandAcceptedList:ArrayList<CandItemAcceptedList>
    @View(R.id.tx_UserName)
    val tx_UserName: TextView?=null
    @View(R.id.tx_summary)
    val tx_summary: TextView?=null
    @View(R.id.tx_edInfdate1)
    val tx_edInfdate1: TextView?=null
    @View(R.id.edinfval)
    val edinfval: TextView?=null
    @View(R.id.tx_edInfexpdate1)
    val tx_edInfexpdate1: TextView?=null
    @View(R.id.edinfexpval)
    val edinfexpval: TextView?=null
    @View(R.id.tx_SkillsVal)
    val tx_SkillsVal: TextView?=null
    @View(R.id.tx_LanguageVal)
    val tx_LanguageVal: TextView?=null
    @View(R.id.profile_image)
    val profile_image: ImageView?=null
    var skills:String=""
    var langagues:String=""

    init{

        candidateViewModel= ViewModelProviders.of(allCandidates).get(CandidateCardViewModel::class.java)
    }

    @Resolve
    private fun onResolved() {

        if(t.language!=null){

            for(i in 0..t.language.size-1){
                if(i<t.language.size-1)
                    langagues+=t.language[i].nom_langue+", "
                else
                    langagues+=t.language[i].nom_langue+""

            }
        }

        if(t.skills!=null){
            for (i in 0..t.skills.size-1 ){
                if(i<t.skills.size-1)
                    skills+=t.skills[i].nom_skill+", "
                else

                    skills+=t.skills[i].nom_skill+""
            }
        }
        tx_SkillsVal!!.text=skills
        tx_UserName!!.text=t.nom
        tx_summary!!.text="mobile developper"
        tx_edInfexpdate1!!.text=(t.experience_professionel[0].date_entrer).toString().substring(t.experience_professionel[0].date_entrer.toString().length-4,t.experience_professionel[0].date_entrer.toString().length)+"-"+(t.experience_professionel[0].date_sortie).toString().substring(t.experience_professionel[0].date_sortie.toString().length-4,t.experience_professionel[0].date_sortie.toString().length)
        tx_edInfdate1!!.text=(t.education[0].date_entrer).toString().substring(t.education[0].date_entrer.toString().length-4,t.education[0].date_entrer.toString().length)
        Log.d("length",(t.education[0].date_entrer).toString().substring(t.education[0].date_entrer.toString().length-4,t.education[0].date_entrer.toString().length))
        Log.d("length",(t.education[0].date_entrer.toString().length-5).toString())

        tx_LanguageVal!!.text=langagues
        edinfexpval!!.text=t.experience_professionel[0].tache_realiser
        edinfval!!.text=t.education[0].diplome+" at "+t.education[0].nom_etablissement
        Glide.with(mContext).load("http://www.concordrusam.com/wp-content/uploads/2017/10/pro.jpg").into(profile_image!!)

    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")


        mSwipeView.addView(this)
        candidateViewModel.ChangeCandOfferStatus(t._id,OfferInCandReject)!!.observe(allCandidates,object: Observer<Candidate> {
            override fun onChanged(t: Candidate?) {
                Log.d("aded offer to ","candidate with sucess")
            }
        })


        candidateViewModel.ChangeOfferCandetat(id, candofferetaatRejected)!!.observe(allCandidates,object: Observer<Offer> {
            override fun onChanged(t: Offer?) {
                Log.d("cange cand etat","in offer with sucess")
            }
        })

        Toast.makeText(mContext, "Candidate Rejected sucessfully",
                Toast.LENGTH_SHORT).show()

    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")

    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")


        candidateViewModel.ChangeOfferCandetat(id,candofferetaat)!!.observe(allCandidates,object: Observer<Offer> {
            override fun onChanged(t: Offer?) {
                Log.d("cange cand etat","in offer with sucess")
            }
        })
        candidateViewModel.ChangeCandOfferStatus(t._id,acceptedOfferInCand)!!.observe(allCandidates,object: Observer<Candidate> {
            override fun onChanged(t: Candidate?) {
                Log.d("canged offer status ","in cand with sucess")

            }
        })
        Toast.makeText(mContext, "Candidate Accepted sucessfully",
                Toast.LENGTH_SHORT).show()

    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")

    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

    fun saveListInSharedPref(){
        val edit=sharedPrefCand.edit()
        val json= gson.toJson( idCandAcceptedList)
        edit.putString("IdList",json)
        edit.apply()
    }
    fun getListFromSharedPref(){
        val json=sharedPrefCand.getString("IdList",null)
        val type: Type =object: TypeToken<ArrayList<CandItemAcceptedList>>(){}.type
        idCandAcceptedList=gson.fromJson(json,type)
    }


}