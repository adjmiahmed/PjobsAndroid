package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateItemList
import com.internship.ahmedaj.tinderstage.Service.Model.OffreItem
import com.internship.ahmedaj.tinderstage.View.Adapters.OffreRecycleAdapter
import com.internship.ahmedaj.tinderstage.View.Adapters.RecruterCandidateRecycleAdapter

import com.internship.ahmedaj.tinderstage.View.Adapters.RecruterOfferRecycleAdapter
import kotlinx.android.synthetic.main.fragment_offers_rect.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class OffersRectFrag : Fragment() {
    lateinit var recy: RecyclerView

    lateinit var list:ArrayList<OffreItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_offers_rect, container, false)
        list=ArrayList<OffreItem>()
        for(i in 1..5){list.add(OffreItem("","","",""))}

        recy=view.recycleView


        recy.setHasFixedSize(true)
        recy.setLayoutManager(LinearLayoutManager(view.context));
        val adapter= RecruterOfferRecycleAdapter(list,view.context,view)
        recy.adapter=adapter



        return view
    }

}// Required empty public constructor
