package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateItemList
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.OfferItemWithId
import com.internship.ahmedaj.tinderstage.Service.Model.OffreItem
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.View.Adapters.OffreRecycleAdapter
import com.internship.ahmedaj.tinderstage.View.Adapters.RecruterCandidateRecycleAdapter

import com.internship.ahmedaj.tinderstage.View.Adapters.RecruterOfferRecycleAdapter
import com.internship.ahmedaj.tinderstage.ViewModel.AddOfferViewModel
import kotlinx.android.synthetic.main.fragment_offers_rect.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class OffersRectFrag : Fragment() {
    lateinit var recy: RecyclerView
    lateinit var addOffreViewModel: AddOfferViewModel
    lateinit var list:ArrayList<OfferItemWithId>
    var mAuth= FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_offers_rect, container, false)
        recy=view.recycleView
        recy.setHasFixedSize(true)
        recy.setLayoutManager(LinearLayoutManager(view.context));

        addOffreViewModel= ViewModelProviders.of(this).get(AddOfferViewModel::class.java)

        list=ArrayList<OfferItemWithId>()
        addOffreViewModel.getRectByEmail(mAuth.currentUser!!.email!!).observe(this@OffersRectFrag,object:Observer<Recruter>{
            override fun onChanged(t: Recruter?) {
                if(t!=null){

                    addOffreViewModel.getofferByRectID(t._id).observe(this@OffersRectFrag,object:Observer<List<Offer>>{
                        override fun onChanged(t: List<Offer>?) {
                            if(t!=null){
                                for(i in 0 ..t.size-1){
                                    if(t[i]!=null)
                                    list.add(OfferItemWithId(t[i]._id,t[i].nom_entreprise,t[i].titre_job,"",t[i].date_offre))
                                }
                                val adapter= RecruterOfferRecycleAdapter(list,view.context,view)
                                recy.adapter=adapter
                            }
                        }


                    })
                }
            }

        })
      //  for(i in 1..5){list.add(OffreItem("","","",""))}






        return view
    }

}// Required empty public constructor
