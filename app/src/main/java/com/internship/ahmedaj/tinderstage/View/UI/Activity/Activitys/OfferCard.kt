package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.HomeFrag
import com.internship.ahmedaj.tinderstage.ViewModel.OfferCardViewModel
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import java.util.*

/**
 * Created by ahmed aj on 25/04/2018.
 */

@Layout(R.layout.offers_card_view)
class OfferCard(private val frag: HomeFrag,private val mContext:Context, private val mSwipeView: SwipePlaceHolderView, var t: Offer,var id:String) {
    //view model
    var offerCardViewModel:OfferCardViewModel
    //etat condidate
  val eta_cond:String="Applied"
    val date_application=Date()
val candInOffer=CandInOffer(id,date_application,eta_cond)
    val offerInCand=OfferInCand(t._id,date_application,eta_cond)
    @View(R.id.tx_NameEntp)
val tx_NameEntp:TextView?=null
    @View(R.id.tx_jobtitle)
    val tx_jobtitle:TextView?=null
    @View(R.id.tx_money)
    val tx_money:TextView?=null
    @View(R.id.tx_adress)
    val tx_adress:TextView?=null
    @View(R.id.tx_time)
    val tx_time:TextView?=null
    @View(R.id.tx_description)
    val tx_description:TextView?=null
    @View(R.id.tx_SkillsVal)
    val tx_SkillsVal:TextView?=null
    @View(R.id.tx_courseVal)
    val tx_courseVal:TextView?=null
    @View(R.id.tx_levelOfStudyVal)
    val tx_levelOfStudyVal:TextView?=null
    @View(R.id.tx_LanguageVal)
    val tx_LanguageVal:TextView?=null
    @View(R.id.profile_image)
    val profileimg:ImageView?=null
var skills:String=""
    var langagues:String=""

    init{
        offerCardViewModel= ViewModelProviders.of(frag).get(OfferCardViewModel::class.java)

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
        tx_NameEntp!!.text=t.nom_entreprise
        tx_jobtitle!!.text=t.titre_job
        tx_money!!.text=t.salaire.toString()
        tx_adress!!.text="sousse"
        tx_time!!.text="full-time"
        tx_description!!.text=t.description
        tx_SkillsVal!!.text=skills
        tx_levelOfStudyVal!!.text=t.niveau_etude
        tx_LanguageVal!!.text=langagues
        Glide.with(mContext).load("https://cdn4.iconfinder.com/data/icons/new-google-logo-2015/400/new-google-favicon-512.png").into(profileimg!!)

    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
        offerCardViewModel.addCandidateToOffer(t._id,candInOffer)!!.observe(frag,object: Observer<Offer> {
            override fun onChanged(t: Offer?) {
            Log.d("aded condidate to ","offer with sucess")
            }
        })
        offerCardViewModel.addOfferToCandidate(id,offerInCand)!!.observe(frag,object:Observer<Candidate>{
            override fun onChanged(t: Candidate?) {
                Log.d("aded offer to ","Cand with sucess")
            }

        })

        Toast.makeText(mContext, " sucessfully Applied In Offer",
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

}
