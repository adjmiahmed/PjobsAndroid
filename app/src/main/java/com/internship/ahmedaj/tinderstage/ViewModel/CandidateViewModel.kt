package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.OfferRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateViewModel:ViewModel() {
var candRep:CandidateRepository
    var offerRep:OfferRepository
    var rectRep:RecruterRepository
    var mutableLiveDataAllCand: MutableLiveData<List<Candidate>> = MutableLiveData<List<Candidate>>()
    var mutableLiveDataAllOffer: MutableLiveData<List<Offer>> = MutableLiveData<List<Offer>>()
    var mutableLiveDataAllRect: MutableLiveData<List<Recruter>> = MutableLiveData<List<Recruter>>()
    var mutableLiveDataCandByEmail: MutableLiveData<Candidate>? =   null
    var mutableLiveDataRectByEmail: MutableLiveData<Recruter>? = null
var mutableLiveDatagetRectOffers:MutableLiveData<List<Offer>>?=null
    var mutableLiveDatagetOfferCand:MutableLiveData<List<Candidate>>?=null
var mutableLiveDatagetCandidatOffers:MutableLiveData<List<Offer>>?=null
    init {
    candRep= CandidateRepository()
   offerRep= OfferRepository()
rectRep= RecruterRepository()
        Log.d("offer live data ","")
        mutableLiveDataAllOffer=offerRep.getAllOffersLiveData()
        Log.d("cand live data ","")
 mutableLiveDataAllCand=candRep.getAllCandidatesLiveData()
mutableLiveDataAllRect=rectRep.getAllRecrutersLiveData()
    Log.d("this is cand init ","")
}

    fun getData(): MutableLiveData<List<Candidate>> {
        Log.d("hi itsCand viewModel","")
        return mutableLiveDataAllCand
    }
    fun getDataOffer(): MutableLiveData<List<Offer>> {
        Log.d("hi itsCand viewModel","")
        return mutableLiveDataAllOffer
    }
    fun getDatarect(): MutableLiveData<List<Recruter>> {

        return mutableLiveDataAllRect
    }

    fun getCandByEmail(email:String):MutableLiveData<Candidate>
    {
        if(mutableLiveDataCandByEmail==null)
        {
            Log.d("get cond by mail null","mutable live data")
            mutableLiveDataCandByEmail=candRep.getCandidateByEmail(email)
        }
        Log.d("get cond by mail","mutable live data")
        return mutableLiveDataCandByEmail as MutableLiveData<Candidate>
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


fun getRectOffers(id:String):MutableLiveData<List<Offer>>
{
    mutableLiveDatagetRectOffers=rectRep.getRectOffers(id)

    return mutableLiveDatagetRectOffers as MutableLiveData<List<Offer>>

}


fun getOfferCand(id:String):MutableLiveData<List<Candidate>>{

    mutableLiveDatagetOfferCand=offerRep.getOfferCand(id)
    return mutableLiveDatagetOfferCand as MutableLiveData<List<Candidate>>
}

fun getCandidatOffers(id:String):MutableLiveData<List<Offer>>{

    mutableLiveDatagetCandidatOffers=candRep.getCandidatOffers(id)
    return mutableLiveDatagetCandidatOffers as MutableLiveData<List<Offer>>
}

}

