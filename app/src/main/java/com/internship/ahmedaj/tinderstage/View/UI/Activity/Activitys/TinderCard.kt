package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.internship.ahmedaj.tinderstage.Service.Model.Profile
import com.internship.ahmedaj.tinderstage.R
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeInState
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState

/**
 * Created by ahmed aj on 23/03/2018.
 */

@Layout(R.layout.tinder_card_view)
class TinderCard(private val mContext: Context, private val mProfile: Profile, private val mSwipeView: SwipePlaceHolderView) {

    @View(R.id.profileImageView)
    private val profileImageView: ImageView? = null

    @View(R.id.nameAgeTxt)
    private val nameAgeTxt: TextView? = null

    @View(R.id.locationNameTxt)
    private val locationNameTxt: TextView? = null

    @Resolve
    private fun onResolved() {
        Glide.with(mContext).load(mProfile.imageUrl).into(profileImageView!!)
        nameAgeTxt!!.text = mProfile.name + ", " + mProfile.age
        locationNameTxt!!.text = mProfile.location
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }
}
