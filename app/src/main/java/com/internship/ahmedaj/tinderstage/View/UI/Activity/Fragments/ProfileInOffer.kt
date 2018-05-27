package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateItemList
import com.internship.ahmedaj.tinderstage.View.Adapters.RecruterCandidateRecycleAdapter
import com.internship.ahmedaj.tinderstage.ViewModel.AddOfferViewModel
import java.util.*
import kotlinx.android.synthetic.main.fragment_profile_in_offer.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileInOffer.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileInOffer.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileInOffer : Fragment() {


    lateinit var recyCandidateProfile: RecyclerView
var offerid=""
    lateinit var addOffreViewModel: AddOfferViewModel
    var listCand=ArrayList<CandidateItemList>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      var view=  inflater.inflate(R.layout.fragment_profile_in_offer, container, false)
recyCandidateProfile=view.Rv_CandProfile
        recyCandidateProfile.setHasFixedSize(true)
        recyCandidateProfile.setLayoutManager(LinearLayoutManager(view.context));
        addOffreViewModel = ViewModelProviders.of(this).get(AddOfferViewModel::class.java)


        val bundel: Bundle? = this@ProfileInOffer.arguments
        if (bundel != null) {
            offerid = bundel.getString("OfferId", "")
        }
addOffreViewModel.getOfferCand(offerid).observe(this@ProfileInOffer,object:Observer<List<Candidate>>{
    override fun onChanged(t: List<Candidate>?) {

        if(t!=null)
        {
for(i in 0..t.size-1)
{
    if(t[i]!=null){

        listCand.add(CandidateItemList(t[i]._id,t[i].nom,"hhmm", Date(),t[i].url_img))

        val adapterCandidateProfile= RecruterCandidateRecycleAdapter(listCand,view.context,view)
        recyCandidateProfile.adapter=adapterCandidateProfile
    }


}
        }
    }


})




        return view
    }


}// Required empty public constructor
