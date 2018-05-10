package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys.OfferCard
import com.internship.ahmedaj.tinderstage.ViewModel.CandidateViewModel
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import me.alexrs.wavedrawable.WaveDrawable

/**
 * A simple [Fragment] subclass.
 */
class HomeFrag : Fragment() {
    private lateinit var mSwipeView: SwipePlaceHolderView
    private lateinit var mContext: Context
private lateinit var candidateViewModel: CandidateViewModel
    lateinit var interpolator : Interpolator
    lateinit var img: ImageView
    lateinit var   waveDrawable:WaveDrawable
    lateinit var layout:RelativeLayout
    var mAuth: FirebaseAuth=FirebaseAuth.getInstance()
    var id_cond:String=""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater!!.inflate(R.layout.fragment_home, container, false)
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



        candidateViewModel.getCandByEmail(mAuth.currentUser!!.email!!).observe(this@HomeFrag,object: Observer<Candidate> {
            override fun onChanged(cand: Candidate?) {
if(cand!=null)
{
    candidateViewModel.getDataOffer().observe(this@HomeFrag,object:Observer<List<Offer>>{
        override fun onChanged(t: List<Offer>?) {

            img.visibility=View.GONE
            mSwipeView.visibility=View.VISIBLE
            waveDrawable.stopAnimation();

            for (i in 0..t!!.size-1){
                var Offree=t[i]
                Log.d("offer number"+i+"is",t[i].toString())
                //"5accdd1c6a5d8a187c344053"
                mSwipeView!!.addView(OfferCard(this@HomeFrag,mContext,mSwipeView,Offree,cand._id))

            }

        }
    })

}

            }})



        candidateViewModel.getDatarect().observe(this,object: Observer<List<Recruter>>{
            override fun onChanged(t: List<Recruter>?) {

                for (i in 0..t!!.size-1){
                    Log.d("rect number"+i+"is",t[i].toString())
                }
            }

        })

/*
        for (profile in Utils.loadProfiles(view.context)!!) {
            mSwipeView!!.addView(TinderCard(mContext, profile, mSwipeView))
        }
*/

/*
for(i in 1..5)
{
    mSwipeView!!.addView(OfferCard(mContext,mSwipeView))

}
*/
        return view


    }
}// Required empty public constructor
