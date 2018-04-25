package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Profile
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import kotlinx.android.synthetic.main.fragment_chat.view.*

/**
 * Created by ahmed aj on 25/04/2018.
 */

/**
 * Created by ahmed aj on 23/03/2018.
 */

@Layout(R.layout.offers_card_view)
class OfferCard(private val mContext: Context, private val mSwipeView: SwipePlaceHolderView) {
    @View(R.id.tx_description)
val text:TextView?=null
    @Resolve
    private fun onResolved() {
text!!.setMovementMethod(object:ScrollingMovementMethod(){})
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
