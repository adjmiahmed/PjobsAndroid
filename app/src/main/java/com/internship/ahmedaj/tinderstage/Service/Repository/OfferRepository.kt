package com.internship.ahmedaj.tinderstage.Service.Repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.CandInOffer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.OfferNoId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ahmed aj on 02/05/2018.
 */
class OfferRepository {
    companion object {
        var apiservice = APIService.create()
    }

    fun createOffer(offer: OfferNoId): MutableLiveData<Offer> {
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()


        apiservice.CreateOffer(offer).enqueue(object : Callback<Offer> {
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on offer list", t!!.message)
            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {

                mutableLiveData.value = response!!.body()
                Log.d("sucess result is :", mutableLiveData.value.toString())
            }
        })
        return mutableLiveData
    }


    fun updateOffer(id: String, offer: OfferNoId): MutableLiveData<Offer> {
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()


        apiservice.updateOffer(id, offer).enqueue(object : Callback<Offer> {
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on offer list", t!!.message)

            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {

                mutableLiveData.value = response!!.body()
                Log.d("sucess result is :", mutableLiveData.value.toString())

            }


        })
        return mutableLiveData
    }
fun getofferByRectID(id:String):MutableLiveData<List<Offer>>{
    var mutableLiveData: MutableLiveData<List<Offer>> = MutableLiveData()

    apiservice.getofferByRectID(id).enqueue(object:Callback<List<Offer>>{
        override fun onFailure(call: Call<List<Offer>>?, t: Throwable?) {
            Log.d("failer on offer list", t!!.message)
        }

        override fun onResponse(call: Call<List<Offer>>?, response: Response<List<Offer>>?) {

            mutableLiveData.value = response!!.body()
            Log.d("sucess result is :", mutableLiveData.value.toString())

        }


    })
    return mutableLiveData
}

    fun getOfferByid(id:String): MutableLiveData<Offer> {
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()
        apiservice.getOfferById(id).enqueue(object:Callback<Offer>{
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on offer list", t!!.message)      }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {
                mutableLiveData.value = response!!.body()
                Log.d("sucess result is :", mutableLiveData.value.toString())
            }

        })
        return mutableLiveData
    }
    fun getAllOffersLiveData(): MutableLiveData<List<Offer>> {
        var mutableLiveData: MutableLiveData<List<Offer>> = MutableLiveData()
        Log.d("hi its all cond retrof", "")
        apiservice.getAllOffers().enqueue(object : Callback<List<Offer>> {
            override fun onFailure(call: Call<List<Offer>>?, t: Throwable?) {
                Log.d("failer on offer list", t!!.message)
                Log.d("failer on offer list", t.localizedMessage)

            }

            override fun onResponse(call: Call<List<Offer>>?, response: Response<List<Offer>>?) {
                Log.d("on response list offer", "")
                mutableLiveData.value = response!!.body()

            }
        })



        return mutableLiveData
    }

    fun addCandToOffre(id: String, body: CandInOffer): MutableLiveData<Offer> {
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()
        apiservice.addCandToOffre(id, body).enqueue(object : Callback<Offer> {
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on adding  ", "cand offer" + t!!.message)
            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {
                Log.d("success on adding  ", "cand offer" + response!!.body().toString())
                mutableLiveData.value = response!!.body()

            }

        })
        return mutableLiveData
    }

    fun ChangeOfferCandetat(id: String, body: CandInOffer): MutableLiveData<Offer> {
        var mutableLiveData: MutableLiveData<Offer> = MutableLiveData()
        apiservice.ChangeOfferCandetat(id, body).enqueue(object : Callback<Offer> {
            override fun onFailure(call: Call<Offer>?, t: Throwable?) {
                Log.d("failer on changing  ", "offercand etat" + t!!.message)
            }

            override fun onResponse(call: Call<Offer>?, response: Response<Offer>?) {
                Log.d("success on changing  ", "offercand etat" + response!!.body().toString())
                mutableLiveData.value = response!!.body()

            }


        })

        return mutableLiveData
    }

    fun getOfferCand(id: String): MutableLiveData<List<Candidate>> {
        var mutableLiveData: MutableLiveData<List<Candidate>> = MutableLiveData()

        apiservice.getOfferCand(id).enqueue(object : Callback<List<Candidate>> {
            override fun onFailure(call: Call<List<Candidate>>?, t: Throwable?) {
                Log.d("failer on offer  ", "cand" + t!!.message)

            }

            override fun onResponse(call: Call<List<Candidate>>?, response: Response<List<Candidate>>?) {
                Log.d("Sucess on offer  ", "cand")
                mutableLiveData.value = response!!.body()

            }

        })
        return mutableLiveData
    }


}