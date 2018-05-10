package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.OffreItem
import com.internship.ahmedaj.tinderstage.View.Adapters.OffreRecycleAdapter
import com.internship.ahmedaj.tinderstage.ViewModel.CandidateViewModel
import me.alexrs.wavedrawable.WaveDrawable


class OffersFrag : Fragment() {
lateinit var recy:RecyclerView
   // lateinit var recyy: SwipeHorizontalMenuLayout
    lateinit var list:ArrayList<OffreItem>
    var mAuth=FirebaseAuth.getInstance()
lateinit var candidateViewModel: CandidateViewModel
    lateinit var   waveDrawable: WaveDrawable
    lateinit var interpolator : Interpolator
    lateinit var layout: RelativeLayout
    lateinit var img: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_offers, container, false)
        candidateViewModel= ViewModelProviders.of(this).get(CandidateViewModel::class.java)
        recy=view.findViewById(R.id.recycleView)
        img=view.findViewById(R.id.imageWave)
        layout=view.findViewById(R.id.rel1)
        interpolator= LinearInterpolator()
        waveDrawable = WaveDrawable(Color.parseColor("#8e44ad"), 500)
        layout.setBackgroundDrawable(waveDrawable)
        waveDrawable.setWaveInterpolator(interpolator);
        waveDrawable.startAnimation();

        list=ArrayList<OffreItem>()


        candidateViewModel.getCandByEmail(mAuth.currentUser!!.email.toString()).observe(this@OffersFrag,object:Observer<Candidate>{
            override fun onChanged(cand: Candidate?) {
                if(cand!=null) {
                    candidateViewModel.getCandidatOffers(cand._id).observe(this@OffersFrag,object:Observer<List<Offer>>{
                        override fun onChanged(t: List<Offer>?) {
                            if(t!=null) {
                                var etat=""

                                for (i in 0..t.size - 1)
                                {
                                for(j in 0..t[i].candidates.size-1)
                                    {
                                        if(cand._id.equals(t[i].candidates[j]._id))
                                        {
                                           etat=t[i].candidates[j].etat_candidate

                                        }

                                    }

                                    img.visibility=View.GONE
                                    waveDrawable.stopAnimation();
                                    recy.visibility=View.VISIBLE

                                    list.add(OffreItem(t[i].nom_entreprise,t[i].titre_job,"",etat))
                                }
                                Log.d("List",list.size.toString())

                                //   recyy=view.findViewById((R.id.sml))
                                recy.setHasFixedSize(true)
                                recy.setLayoutManager(LinearLayoutManager(view.context));
                                val adapter= OffreRecycleAdapter(list,view.context,view)
                                recy.adapter=adapter



                            }
                        }


                    })
                }

            }

        })

       recy=view.findViewById(R.id.recycleView)
     //   recyy=view.findViewById((R.id.sml))
        recy.setHasFixedSize(true)
        recy.setLayoutManager(LinearLayoutManager(view.context));
        val adapter= OffreRecycleAdapter(list,view.context,view)
        recy.adapter=adapter
        return view
    }

}
