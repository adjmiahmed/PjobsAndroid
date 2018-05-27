package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.RecruterNoId
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 21/05/2018.
 */
class EditProfileViewModel:ViewModel() {
    var candRep: CandidateRepository
    var recRep:RecruterRepository
    var mutableLiveDataEditCand: MutableLiveData<Candidate>? =   null
    var mutableLiveDataCandByEmail: MutableLiveData<Candidate>? =   null
    var mutableLiveDataCreateCand: MutableLiveData<Candidate>? =   null
   var  mutableLiveDataCreateRect:MutableLiveData<Recruter>? =   null
    init {
        candRep= CandidateRepository()
        recRep= RecruterRepository()
    }
    fun updateCand(id:String,cand:CandidateNoId): MutableLiveData<Candidate>? {
        mutableLiveDataEditCand=candRep.updateCand(id,cand)
        return mutableLiveDataEditCand
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

    fun createCandidate(cand: CandidateNoId):MutableLiveData<Candidate>{

        mutableLiveDataCreateCand= candRep.createCandidate(cand)
        return mutableLiveDataCreateCand as MutableLiveData<Candidate>

    }
    fun createRecruter(rect:RecruterNoId):MutableLiveData<Recruter>{
        mutableLiveDataCreateRect=recRep.createRecruter(rect)
        return mutableLiveDataCreateRect as MutableLiveData<Recruter>
    }

}