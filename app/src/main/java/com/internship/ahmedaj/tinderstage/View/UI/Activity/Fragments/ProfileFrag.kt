package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.Candidate
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.ViewModel.CandidateViewModel
import com.internship.ahmedaj.tinderstage.ViewModel.ProfileFragViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProfileFrag : Fragment() {
lateinit var profiletype:String
lateinit var img:ImageView
    lateinit var profileFragViewModel:ProfileFragViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_profile, container, false)
profiletype="Candidate"
        img=view.findViewById(R.id.imgediter)



img.setOnClickListener {
    Toast.makeText(view.context, "loading detail",
            Toast.LENGTH_SHORT).show()


}

        profileFragViewModel = ViewModelProviders.of(this).get(ProfileFragViewModel::class.java)


        if(profiletype.equals("Candidate"))
        profileFragViewModel.getData().observe(this,object: Observer<List<Candidate>> {
            override fun onChanged(t: List<Candidate>?) {

                for (i in 0..t!!.size-1){
                    Log.d("Cand number"+i+"is",t[i].toString())



                }
            }

        })
        else if(profiletype.equals("Recruter"))
        profileFragViewModel.getDatarect().observe(this,object: Observer<List<Recruter>>{
            override fun onChanged(t: List<Recruter>?) {

                for (i in 0..t!!.size-1){
                    Log.d("rect number"+i+"is",t[i].toString())


                }
            }

        })


        return view
    }


    fun lodingDataInView(view:View):View{

        return view
    }


}// Required empty public constructor
