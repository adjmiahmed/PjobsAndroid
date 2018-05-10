package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.OfferRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 07/05/2018.
 */
class CandidateCardViewModel: ViewModel() {
    var candRep: CandidateRepository
var offerRep:OfferRepository
    var rectRep: RecruterRepository

    var mutableLiveDataCandOfferStatus: MutableLiveData<Candidate>? = null
    var mutableLiveDataofferCandetat: MutableLiveData<Offer>? = null
    var mutableLiveDataRectByEmail: MutableLiveData<Recruter>? = null

    // var mutableLiveDataCandidatetooffer: MutableLiveData<Candidate>? = null


    init{
        candRep= CandidateRepository()
offerRep= OfferRepository()
        rectRep= RecruterRepository()

    }


    fun ChangeCandOfferStatus(id:String,body:OfferInCand):MutableLiveData<Candidate>? {

        mutableLiveDataCandOfferStatus=candRep.ChangeCandOfferStatus(id,body)

        return mutableLiveDataCandOfferStatus
    }
    fun ChangeOfferCandetat(id:String,body: CandInOffer):MutableLiveData<Offer>? {

        mutableLiveDataofferCandetat=offerRep.ChangeOfferCandetat(id,body)

        return mutableLiveDataofferCandetat
    }

    fun getRectByEmail(email:String):MutableLiveData<Recruter>
    {
        Log.d("get cond by mail","mutable live data")
        if(mutableLiveDataRectByEmail==null)
        {
            Log.d("get cond by mail","mutable live data")
            mutableLiveDataRectByEmail=rectRep.getRecruterByEmail(email)
        }
        return mutableLiveDataRectByEmail as MutableLiveData<Recruter>
    }

}