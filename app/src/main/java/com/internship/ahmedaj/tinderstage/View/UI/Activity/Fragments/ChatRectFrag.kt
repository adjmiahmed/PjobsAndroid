package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.internship.ahmedaj.tinderstage.R


/**
 * A simple [Fragment] subclass.
 */
class ChatRectFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_rect, container, false)
    }

}// Required empty public constructor
