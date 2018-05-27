package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.OfferNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Repository.OfferRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 27/05/2018.
 */
class AddOfferViewModel:ViewModel() {
    var offreRep:OfferRepository=OfferRepository()
    var rectRep: RecruterRepository=RecruterRepository()

    var mutableLiveDataCreateOffer:MutableLiveData<Offer>?=null
    var mutableLiveDataUpdateOffer:MutableLiveData<Offer>?=null
    var mutableLiveDataRectByEmail: MutableLiveData<Recruter>? = null
    var mutableLiveDataOffersByRectID: MutableLiveData<List<Offer>> = MutableLiveData()
    var MutableLiveDataListCandidate:MutableLiveData<List<Candidate>>?=null
    var mutableLiveDataGetOfferByID:MutableLiveData<Offer>?=null
    var mutableLiveDataRectByID: MutableLiveData<Recruter>? = null



    fun createOffre(Offre: OfferNoId): MutableLiveData<Offer>? {

        mutableLiveDataCreateOffer=offreRep.createOffer(Offre)
        return mutableLiveDataCreateOffer

    }
    fun UpdateOffre(id: String,Offre:OfferNoId): MutableLiveData<Offer>? {
        mutableLiveDataUpdateOffer=offreRep.updateOffer(id,Offre)
        return mutableLiveDataUpdateOffer

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
fun getofferByRectID(id:String):MutableLiveData<List<Offer>>{
    mutableLiveDataOffersByRectID=offreRep.getofferByRectID(id)
    return mutableLiveDataOffersByRectID

}

    fun getOfferCand(id:String):MutableLiveData<List<Candidate>>{
        MutableLiveDataListCandidate=offreRep.getOfferCand(id)
        return MutableLiveDataListCandidate as MutableLiveData<List<Candidate>>
    }

    fun getOfferbyId(id:String):MutableLiveData<Offer>{
        mutableLiveDataGetOfferByID=offreRep.getOfferByid(id)
        return mutableLiveDataGetOfferByID as MutableLiveData<Offer>

    }
fun getRectById(id:String):MutableLiveData<Recruter>{
    mutableLiveDataRectByID=rectRep.getRectById(id)
   return mutableLiveDataRectByID as MutableLiveData<Recruter>
} }