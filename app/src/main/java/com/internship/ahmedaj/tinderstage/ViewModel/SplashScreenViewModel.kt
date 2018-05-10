package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 06/05/2018.
 */
class SplashScreenViewModel:ViewModel() {
    var candRep: CandidateRepository
    var rectRep: RecruterRepository

    var mutableLiveDataRectByEmail: MutableLiveData<Recruter>? = null
    var mutableLiveDataCandByEmail: MutableLiveData<Candidate>? =   null

    init {
        candRep= CandidateRepository()
        rectRep= RecruterRepository()
    }

    fun getCandByEmail(email:String): MutableLiveData<Candidate>
    {
        if(mutableLiveDataCandByEmail==null)
        {
            Log.d("get cond by mail null","mutable live data")
            mutableLiveDataCandByEmail=candRep.getCandidateByEmail(email)
        }
        Log.d("get cond by mail","mutable live data")
        return mutableLiveDataCandByEmail as MutableLiveData<Candidate>
    }

    fun getRectByEmail(email:String): MutableLiveData<Recruter>
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