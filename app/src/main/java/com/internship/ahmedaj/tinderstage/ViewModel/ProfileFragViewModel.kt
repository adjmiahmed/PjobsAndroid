package com.internship.ahmedaj.tinderstage.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.Service.Repository.CandidateRepository
import com.internship.ahmedaj.tinderstage.Service.Repository.RecruterRepository

/**
 * Created by ahmed aj on 02/05/2018.
 */
class ProfileFragViewModel :ViewModel() {

    var candRep: CandidateRepository
    var rectRep: RecruterRepository
    var mutableLiveDataAllCand: MutableLiveData<List<Candidate>> = MutableLiveData<List<Candidate>>()
    var mutableLiveDataAllRect: MutableLiveData<List<Recruter>> = MutableLiveData<List<Recruter>>()



    init {
        candRep= CandidateRepository()
        rectRep= RecruterRepository()
        mutableLiveDataAllCand=candRep.getAllCandidatesLiveData()
        mutableLiveDataAllRect=rectRep.getAllRecrutersLiveData()

    }

    fun getData(): MutableLiveData<List<Candidate>> {
        Log.d("hi itsCand viewModel","")
        return mutableLiveDataAllCand
    }

    fun getDatarect(): MutableLiveData<List<Recruter>> {

        return mutableLiveDataAllRect
    }
}