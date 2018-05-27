package com.internship.ahmedaj.tinderstage.Service.Repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateNoId
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.OfferInCand
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ahmed aj on 02/05/2018.
 */
class CandidateRepository {
    companion object {
        var apiservice= APIService.create()
    }

fun createCandidate(cand:CandidateNoId):MutableLiveData<Candidate>
{
    var mutableLiveData: MutableLiveData <Candidate> = MutableLiveData()

    apiservice.CreateCandidate(cand).enqueue(object:Callback<Candidate>{
        override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {
            Log.d("","onSucess Call")
            mutableLiveData.value=response!!.body()
            Log.d("created with sucess",""+ mutableLiveData.value.toString())
        }

        override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
            Log.d("failllllle","error"+t)
        }


    })

return mutableLiveData
}
    fun updateCand(id:String,cand:CandidateNoId):MutableLiveData<Candidate>
    {
        var mutableLiveData: MutableLiveData <Candidate> = MutableLiveData()
        apiservice.updateCandidate(id,cand).enqueue(object:Callback<Candidate>{
            override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
                Log.d("failllllle","error"+t)
            }

            override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {

                Log.d("","onSucess Call")
                mutableLiveData.value=response!!.body()
                Log.d("created with sucess",""+ mutableLiveData.value.toString())

            }
        })

return mutableLiveData
    }
    fun updateCandidate(id:String,cand:CandidateNoId):MutableLiveData<Candidate>
    {
        var mutableLiveData: MutableLiveData <Candidate> = MutableLiveData()

        apiservice.updateCandidate(id,cand).enqueue(object:Callback<Candidate>{
            override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {
                Log.d("","onSucess Call")
                mutableLiveData.value=response!!.body()
                Log.d("created with sucess",""+ mutableLiveData.value.toString())
            }

            override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
                Log.d("failllllle","error"+t)
            }

        })

        return mutableLiveData
    }



    fun getAllCandidatesLiveData(): MutableLiveData<List<Candidate>> {
        var mutableLiveData: MutableLiveData <List<Candidate>> = MutableLiveData()
        Log.d("hi its all cond retrof","")
        apiservice.getAllCandidates().enqueue(object: Callback<List<Candidate>> {
            override fun onResponse(call: Call<List<Candidate>>?, response: Response<List<Candidate>>?) {

                Log.d("","onSucess Call")
                mutableLiveData.value = response!!.body()
                        Log.d("mutable live data",mutableLiveData.value.toString())
            }

            override fun onFailure(call: Call<List<Candidate>>?, t: Throwable?) {
                Log.d("","oFailure Call")

            }

        })



        return  mutableLiveData
    }



    fun getCandidateByEmail(email:String):MutableLiveData<Candidate>
    {
        var mutableLiveData: MutableLiveData <Candidate> = MutableLiveData()

        apiservice.getCandbyEmail(email).enqueue(object:Callback<Candidate>{
            override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
                Log.d("","oFailure Call")

            }
            override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {
            mutableLiveData.value=response!!.body()
                Log.d("response candidate ","  email sucessful")

            }

        })

        return mutableLiveData
    }


    fun addOffreToCand(id:String, body: OfferInCand):MutableLiveData<Candidate>{
        var mutableLiveData: MutableLiveData<Candidate> = MutableLiveData()
        apiservice.addOffreToCand(id,body).enqueue(object:Callback<Candidate>{
            override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
                Log.d("failer on adding  ","offer cand "+t!!.message)
            }

            override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {
                Log.d("success on adding  ","offer cand "+response!!.body().toString())
                mutableLiveData.value=response!!.body()

            }

        })
        return mutableLiveData
    }

    fun ChangeCandOfferStatus(id:String, body: OfferInCand):MutableLiveData<Candidate>{
        var mutableLiveData: MutableLiveData<Candidate> = MutableLiveData()
        apiservice.ChangeCandOfferStatus(id,body).enqueue(object:Callback<Candidate>{
            override fun onFailure(call: Call<Candidate>?, t: Throwable?) {
                Log.d("failer on adding  ","offer cand "+t!!.message)
            }

            override fun onResponse(call: Call<Candidate>?, response: Response<Candidate>?) {
                Log.d("success on adding  ","offer cand "+response!!.body().toString())
                mutableLiveData.value=response!!.body()

            }

        })
        return mutableLiveData
    }

fun getCandidatOffers(id:String):MutableLiveData<List<Offer>>{
    var mutableLiveData: MutableLiveData<List<Offer>> = MutableLiveData()

    apiservice.getCandidatOffers(id).enqueue(object :Callback<List<Offer>>{
        override fun onFailure(call: Call<List<Offer>>?, t: Throwable?) {

            Log.d("failer on getting  ","offer cand "+t!!.message)
        }

        override fun onResponse(call: Call<List<Offer>>?, response: Response<List<Offer>>?) {

            Log.d("failer on getting  ","offer cand ")
        mutableLiveData.value=response!!.body()

        }
    })

    return mutableLiveData
}


}