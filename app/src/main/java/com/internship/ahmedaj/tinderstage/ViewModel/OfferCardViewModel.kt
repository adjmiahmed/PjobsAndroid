package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.OfferRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 07/05/2018.
 */
class OfferCardViewModel:ViewModel() {
    var offerRep: OfferRepository
    var candRep:CandidateRepository
    var mutableLiveDataaddcondInOffer: MutableLiveData<Offer>? = null
    var mutableLiveDataaddOfferInCand: MutableLiveData<Candidate>? = null

    init{
        offerRep= OfferRepository()
candRep= CandidateRepository()
    }
    fun addCandidateToOffer(id:String,body: CandInOffer): MutableLiveData<Offer>? {

    mutableLiveDataaddcondInOffer=offerRep.addCandToOffre(id,body)

        return mutableLiveDataaddcondInOffer
    }

    fun addOfferToCandidate(id:String,body: OfferInCand): MutableLiveData<Candidate>? {

        mutableLiveDataaddOfferInCand=candRep.addOffreToCand(id,body)

        return mutableLiveDataaddOfferInCand
    }


}