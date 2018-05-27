package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.*
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.ViewModel.AddOfferViewModel
import kotlinx.android.synthetic.main.fragment_add_offre.view.*
import java.text.SimpleDateFormat
import java.util.*


class EditOffer : Fragment() {
    var formatter: SimpleDateFormat = SimpleDateFormat("yyyy")
    lateinit var candidates: ArrayList<OfferCandiates>
    lateinit var skills: ArrayList<OfferSkillNoId>
    lateinit var language: ArrayList<OfferLanguageNoId>
    lateinit var offre: OfferNoId
    lateinit var addOffreViewModel: AddOfferViewModel
    var mySkills = ""
    var myLang = ""
    var mAuth = FirebaseAuth.getInstance()
    var offerid: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_edit_offer, container, false)

        addOffreViewModel = ViewModelProviders.of(this).get(AddOfferViewModel::class.java)
        skills = ArrayList()
        language = ArrayList()
        candidates = ArrayList()

        val bundel: Bundle? = this@EditOffer.arguments
        if (bundel != null) {
            offerid = bundel.getString("OfferId", "")
        }
        skills.add(OfferSkillNoId("", view.tx_SkillsVal.text.toString()))
        skills.add(OfferSkillNoId("", view.tx_SkillsVal1.text.toString()))
        skills.add(OfferSkillNoId("", view.tx_SkillsVal2.text.toString()))
        language.add(OfferLanguageNoId("", view.tx_LanguageVal.text.toString()))
        language.add(OfferLanguageNoId("", view.tx_LanguageVal1.text.toString()))
        view.bt_saveoffer.setOnClickListener {
            Log.d("aaaaaaaaaaaaaaaaaaaa", "button click")
            addOffreViewModel.getRectByEmail(mAuth.currentUser!!.email!!).observe(this@EditOffer, object : android.arch.lifecycle.Observer<Recruter> {
                override fun onChanged(t: Recruter?) {
                    if (t != null) {
                        offre = OfferNoId(view.tx_jobtitle.text.toString(), view.tx_description.text.toString(), Integer.parseInt(view.tx_money.text.toString()), Date(), view.tx_courseVal.text.toString(), view.tx_levelOfStudyVal.text.toString(), "active", t._id, view.tx_NameEntp.text.toString(), candidates, language, skills)
                        addOffreViewModel.UpdateOffre(offerid, offre)!!.observe(this@EditOffer, object : Observer<Offer> {
                            override fun onChanged(o: Offer?) {
                                if (o != null) {
                                    Toast.makeText(view.context, "Offer added with sucess", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })

                    }

                }


            })
        }





        return view
    }

}// Required empty public constructor
