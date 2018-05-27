package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Offer.Offer
import com.internship.ahmedaj.tinderstage.Service.Model.Recruter.Recruter
import com.internship.ahmedaj.tinderstage.ViewModel.AddOfferViewModel
import kotlinx.android.synthetic.main.fragment_info_offer.view.*


class InfoOffer : Fragment() {
    var offerid = ""
    lateinit var addOffreViewModel: AddOfferViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_info_offer, container, false)
        addOffreViewModel = ViewModelProviders.of(this).get(AddOfferViewModel::class.java)


        val bundel: Bundle? = this@InfoOffer.arguments
        if (bundel != null) {
            offerid = bundel.getString("OfferId", "")
        }
        addOffreViewModel.getOfferbyId(offerid).observe(this@InfoOffer, object : Observer<Offer> {
            override fun onChanged(o: Offer?) {
                if (o != null) {
                    addOffreViewModel.getRectById(o.id_recruteur).observe(this@InfoOffer, object : Observer<Recruter> {
                        override fun onChanged(rect: Recruter?) {
                            if (rect != null) {
                                var skill = ""
                                for (i in 0..o.skills.size - 1) {
                                    if (i < o.skills.size - 1)
                                        skill += o.skills[i].nom_skill + ","
                                    else
                                        skill += o.skills[i].nom_skill + ""
                                }
                                var lang = ""
                                for (i in 0..o.language.size - 1) {
                                    if (i < o.language.size - 1)
                                        lang += o.language[i].nom_langue + ","
                                    else
                                        lang += o.language[i].nom_langue + ""
                                }
                                view.tx_NameEntp.text = o.nom_entreprise
                                view.tx_jobtitle.text = o.titre_job
                                view.tx_adress.text = rect.entreprise.adress_entreprise
                                view.tx_money.text = o.salaire.toString()
                                view.tx_description.text = o.description
                                view.tx_courseVal.text = o.background
                                view.tx_SkillsVal.text = skill
                                view.tx_LanguageVal.text = lang
                                view.tx_courseVal.text = o.background
                                view.tx_levelOfStudyVal.text=o.niveau_etude
                            }
                        }
                    })


                }
            }

        })

        return view
    }

}// Required empty public constructor
