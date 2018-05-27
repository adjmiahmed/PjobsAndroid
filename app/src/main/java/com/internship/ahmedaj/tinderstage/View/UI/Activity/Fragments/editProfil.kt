package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.*
import com.internship.ahmedaj.tinderstage.ViewModel.EditProfileViewModel
import kotlinx.android.synthetic.main.fragment_edit_profil.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class editProfil : Fragment() {


    var formatter: SimpleDateFormat = SimpleDateFormat("yyyy")

    //skills spinner
    lateinit var spin: Spinner
    lateinit var spin2: Spinner
    lateinit var spin3: Spinner
    //language spinner
    lateinit var spinlang1: Spinner
    lateinit var spinlang2: Spinner
    lateinit var spinlang3: Spinner


    lateinit var bt_save: Button
    //skills val
    var skillVal1 = "Not selected"
    var skillVal2 = "Not selected"
    var skillVal3 = "Not selected"
    //langue val
    var langVal1 = "Not selected"
    var langVal2 = "Not selected"


    lateinit var list: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>
    var CandidateProfile: CandidateNoId? = null
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var editprof: EditProfileViewModel
    lateinit var candEd: ArrayList<CandidateEdcationNoId>
    lateinit var candExp: ArrayList<CandidateExperienceNoId>
    lateinit var candidateSkills: ArrayList<CandidateSkillsNoId>
    lateinit var candidateProjects: ArrayList<CandidateProjectsNoId>
    lateinit var candidateLanguage: ArrayList<CandidateLanguageNoId>
    lateinit var candidateOffers: ArrayList<CandidateOffers>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_edit_profil, container, false)
        editprof = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)


        spin = view.sp_education2
        spin2 = view.sp_education3
        spin3 = view.sp_education4
        spinlang1 = view.tx_lang_val
        spinlang2 = view.tx_lang_val2


        bt_save = view.bt_saveprofile

        bt_save.setOnClickListener {

            candEd = ArrayList<CandidateEdcationNoId>()
            candExp = ArrayList<CandidateExperienceNoId>()
            candidateSkills = ArrayList<CandidateSkillsNoId>()
            candidateProjects = ArrayList<CandidateProjectsNoId>()
            candidateOffers = ArrayList<CandidateOffers>()
            candidateLanguage = ArrayList<CandidateLanguageNoId>()

            //candidate education initialisation
            candEd.add(CandidateEdcationNoId(view.tx_education2.text.toString(), formatter.parse(view.tx_date2.text.toString()), view.tx_educationDiplome.text.toString()))
            candEd.add(CandidateEdcationNoId(view.tx_education2.text.toString(), formatter.parse(view.tx_date2.text.toString()), view.tx_educationDiplome.text.toString()))

            //candidate experience initialisation
            candExp.add(CandidateExperienceNoId(view.tx_ExpEntreprise0.text.toString(), view.tx_ExpTitle0.text.toString(), view.tx_tacheRealise0.text.toString(), formatter.parse(view.tx_Expdate0.text.toString()), formatter.parse(view.tx_Expdate2.text.toString())))
            candExp.add(CandidateExperienceNoId(view.tx_ExpEntreprise.text.toString(), view.tx_ExpTitle.text.toString(), view.tx_tacheRealise.text.toString(), formatter.parse(view.tx_Expdate.text.toString()), formatter.parse(view.tx_Expdate1.text.toString())))

            //candidate Projects initialisation
            candidateProjects.add(CandidateProjectsNoId(view.tx_projectName.text.toString(), formatter.parse(view.tx_Projdate.text.toString()), formatter.parse(view.tx_Projdate1.text.toString()), view.tx_ProjetDesc.text.toString(), view.tx_ProjetTacheRealise.text.toString()))
            candidateProjects.add(CandidateProjectsNoId(view.tx_projectName1.text.toString(), formatter.parse(view.tx_Projdate0.text.toString()), formatter.parse(view.tx_Projdate2.text.toString()), view.tx_ProjetDesc1.text.toString(), view.tx_ProjetTacheRealise1.text.toString()))

            //cnaidate skills initilaisation
            candidateSkills.add(CandidateSkillsNoId(view.tx_skilname.text.toString(), skillVal1))
            candidateSkills.add(CandidateSkillsNoId(view.tx_skilname.text.toString(), skillVal2))
            candidateSkills.add(CandidateSkillsNoId(view.tx_skilname.text.toString(), skillVal3))

            //candidate language initialisation
            candidateLanguage.add(CandidateLanguageNoId(view.tx_lang_name.text.toString(), langVal1))
            candidateLanguage.add(CandidateLanguageNoId(view.tx_lang_name2.text.toString(), langVal2))

            candidateOffers.add(CandidateOffers("", "", ""))

            CandidateProfile = CandidateNoId(view.tx_username.text.toString(), mAuth.currentUser!!.email!!.toString(), "", Date(), Integer.parseInt(view.tx_Phoneval.text.toString()), view.tx_adressval.text.toString(), "", "Homme", candidateOffers, candExp, candidateSkills, candEd, candidateProjects, candidateLanguage)


//create profile
            /*        editprof.createCandidate(this!!.CandidateProfile!!).observe(this@editProfil,object:android.arch.lifecycle.Observer<Candidate>{
                       override fun onChanged(t: Candidate?) {
                           if(t!=null)
                               Toast.makeText(view.context, "Profile edit with sucess", Toast.LENGTH_SHORT).show()
                           else
                               Toast.makeText(view.context, "Fail to edit profile with sucess", Toast.LENGTH_SHORT).show()
                       }
                   } )

        */
            editprof.getCandByEmail(mAuth.currentUser!!.email!!).observe(this@editProfil, object : android.arch.lifecycle.Observer<Candidate> {
                override fun onChanged(t: Candidate?) {
                    if (t != null) {
                        editprof.updateCand(t._id, CandidateProfile!!)!!.observe(this@editProfil, object : android.arch.lifecycle.Observer<Candidate> {
                            override fun onChanged(t: Candidate?) {
                                Toast.makeText(view.context, "Profile edit with sucess", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }

                }

            })


        }

        list = ArrayList()
        list.add("none")
        list.add("Beginner")
        list.add("Intermediere")
        list.add("Advanced")
        adapter = ArrayAdapter<String>(view.context,
                android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //skill spinner initalisation
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        skillVal1 = "not specified"
                        Log.d("none is slected", "")
                    }
                    1 -> {
                        skillVal1 = "Beginner"
                        Log.d("Beginner", "is slected")
                    }
                    2 -> {
                        skillVal1 = "Intermediere"
                        Log.d("Intermediere", "is slected")
                    }
                    3 -> {
                        skillVal1 = "Advanced"
                        Log.d("Advanced", "is slected")
                    }

                }
            }
        });

        spin3.setAdapter(adapter);
        spin3.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        skillVal2 = "not specified"
                        Log.d("none is slected", "")
                    }
                    1 -> {
                        skillVal2 = "Beginner"
                        Log.d("Beginner", "is slected")
                    }
                    2 -> {
                        skillVal2 = "Intermediere"
                        Log.d("Intermediere", "is slected")
                    }
                    3 -> {
                        skillVal2 = "Advanced"
                        Log.d("Advanced", "is slected")
                    }
                }
            }
        })
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        skillVal3 = "not specified"
                        Log.d("none is slected", "")
                    }
                    1 -> {
                        skillVal3 = "Beginner"
                        Log.d("Beginner", "is slected")
                    }
                    2 -> {
                        skillVal3 = "Intermediere"
                        Log.d("Intermediere", "is slected")
                    }
                    3 -> {
                        skillVal3 = "Advanced"
                        Log.d("Advanced", "is slected")
                    }

                }
            }
        })

        //language spinner initialisation
        spinlang1.setAdapter(adapter);
        spinlang1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {

                    0 -> {
                        langVal1 = "not specified"
                        Log.d("none is slected", "")
                    }
                    1 -> {
                        langVal1 = "Beginner"
                        Log.d("Beginner", "is slected")
                    }
                    2 -> {
                        langVal1 = "Intermediere"
                        Log.d("Intermediere", "is slected")
                    }
                    3 -> {
                        langVal1 = "Advanced"
                        Log.d("Advanced", "is slected")
                    }


                }
            }
        });

        spinlang2.setAdapter(adapter);
        spinlang2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {

                    0 -> {
                        langVal2 = "not specified"
                        Log.d("none is slected", "")
                    }
                    1 -> {
                        langVal2 = "Beginner"
                        Log.d("Beginner", "is slected")
                    }
                    2 -> {
                        langVal2 = "Intermediere"
                        Log.d("Intermediere", "is slected")
                    }
                    3 -> {
                        langVal2 = "Advanced"
                        Log.d("Advanced", "is slected")
                    }


                }
            }
        })

        return view
    }

}// Required empty public constructor
