package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.internship.ahmedaj.tinderstage.R
import android.util.Log
import android.view.animation.Interpolator


import android.view.animation.LinearInterpolator
import android.widget.*
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys.CandidateCard
import me.alexrs.wavedrawable.WaveDrawable

import com.internship.ahmedaj.tinderstage.ViewModel.CandidateViewModel
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder


/**
 * A simple [Fragment] subclass.
 */
class ChatFrag : Fragment() {
    private lateinit var mSwipeView: SwipePlaceHolderView
    private lateinit var mContext: Context
    private lateinit var candidateViewModel: CandidateViewModel
    lateinit var interpolator : Interpolator
    lateinit var img: ImageView
    lateinit var   waveDrawable:WaveDrawable
    lateinit var layout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_chat, container, false)
        layout=view.findViewById(R.id.rel1)
        img=view.findViewById(R.id.imageWave)
        interpolator= LinearInterpolator()
        waveDrawable = WaveDrawable(Color.parseColor("#8e44ad"), 500)
        layout.setBackgroundDrawable(waveDrawable)

        waveDrawable.setWaveInterpolator(interpolator);
        waveDrawable.startAnimation();

        candidateViewModel= ViewModelProviders.of(this).get(CandidateViewModel::class.java)
        Log.d("hi its Home fragment","")

        mSwipeView = view.findViewById(R.id.swipeView)
        // findViewById<View>(R.id.swipeView) as SwipePlaceHolderView
        mContext = view.context
        mSwipeView!!.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
                .setDisplayViewCount(2)
                .setSwipeDecor(SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view))


        candidateViewModel.getData().observe(this,object: Observer<List<Candidate>> {
            override fun onChanged(t: List<Candidate>?) {
                img.visibility=View.GONE
                mSwipeView.visibility=View.VISIBLE
                waveDrawable.stopAnimation();

                for (i in 0..t!!.size-1){
                    var cand=t[i]
                    Log.d("Cand number"+i+"is",t[i].toString())
               //     mSwipeView!!.addView(CandidateCard(this@ChatFrag,mContext,mSwipeView,cand,"5acc876c734d1d55c3191b1d"))

                }
            }

        })



        return view
    }

}// Required empty public constructor
