package com.internship.ahmedaj.tinderstage.View.UI.Activity.Activitys

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.*
import com.internship.ahmedaj.tinderstage.ViewModel.EditProfileViewModel
import kotlinx.android.synthetic.main.activity_candidate_profile.*
import java.text.SimpleDateFormat
import java.util.*

class CandidateProfileAct : AppCompatActivity() {

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
lateinit var intentCand: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_profile)
        editprof = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)

//intent initilisation
        intentCand=Intent(this@CandidateProfileAct,NavDrowarAct::class.java)
       intentCand.putExtra("accountType","Candidate")
        spin = sp_education2
        spin2 = sp_education3
        spin3 = sp_education4
        spinlang1 = tx_lang_val
        spinlang2 = tx_lang_val2
        bt_save = bt_saveprofile

        bt_save.setOnClickListener {

            candEd = ArrayList<CandidateEdcationNoId>()
            candExp = ArrayList<CandidateExperienceNoId>()
            candidateSkills = ArrayList<CandidateSkillsNoId>()
            candidateProjects = ArrayList<CandidateProjectsNoId>()
            candidateOffers = ArrayList<CandidateOffers>()
            candidateLanguage = ArrayList<CandidateLanguageNoId>()

            //candidate education initialisation
            candEd.add(CandidateEdcationNoId(tx_education2.text.toString(), formatter.parse(tx_date2.text.toString()), tx_educationDiplome.text.toString()))
            candEd.add(CandidateEdcationNoId(tx_education2.text.toString(), formatter.parse(tx_date2.text.toString()), tx_educationDiplome.text.toString()))

            //candidate experience initialisation
            candExp.add(CandidateExperienceNoId(tx_ExpEntreprise0.text.toString(), tx_ExpTitle0.text.toString(), tx_tacheRealise0.text.toString(), formatter.parse(tx_Expdate0.text.toString()), formatter.parse(tx_Expdate2.text.toString())))
            candExp.add(CandidateExperienceNoId(tx_ExpEntreprise.text.toString(), tx_ExpTitle.text.toString(), tx_tacheRealise.text.toString(), formatter.parse(tx_Expdate.text.toString()), formatter.parse(tx_Expdate1.text.toString())))

            //candidate Projects initialisation
            candidateProjects.add(CandidateProjectsNoId(tx_projectName.text.toString(), formatter.parse(tx_Projdate.text.toString()), formatter.parse(tx_Projdate1.text.toString()), tx_ProjetDesc.text.toString(), tx_ProjetTacheRealise.text.toString()))
            candidateProjects.add(CandidateProjectsNoId(tx_projectName1.text.toString(), formatter.parse(tx_Projdate0.text.toString()), formatter.parse(tx_Projdate2.text.toString()), tx_ProjetDesc1.text.toString(), tx_ProjetTacheRealise1.text.toString()))

            //cnaidate skills initilaisation
            candidateSkills.add(CandidateSkillsNoId(tx_skilname.text.toString(), skillVal1))
            candidateSkills.add(CandidateSkillsNoId(tx_skilname.text.toString(), skillVal2))
            candidateSkills.add(CandidateSkillsNoId(tx_skilname.text.toString(), skillVal3))

            //candidate language initialisation
            candidateLanguage.add(CandidateLanguageNoId(tx_lang_name.text.toString(), langVal1))
            candidateLanguage.add(CandidateLanguageNoId(tx_lang_name2.text.toString(), langVal2))

            candidateOffers.add(CandidateOffers("", "", ""))

            CandidateProfile = CandidateNoId(tx_username.text.toString(), mAuth.currentUser!!.email!!.toString(), "", Date(), Integer.parseInt(tx_Phoneval.text.toString()), tx_adressval.text.toString(), "", "Homme", candidateOffers, candExp, candidateSkills, candEd, candidateProjects, candidateLanguage)

            //create profile
            editprof.createCandidate(this!!.CandidateProfile!!).observe(this@CandidateProfileAct, object : android.arch.lifecycle.Observer<Candidate> {
                override fun onChanged(t: Candidate?) {
                    if (t != null) {
                        Toast.makeText(applicationContext, "Profile Created with sucess", Toast.LENGTH_SHORT).show()
                    startActivity(intentCand)
                    }else
                        Toast.makeText(applicationContext, "Fail to create profile with sucess", Toast.LENGTH_SHORT).show()
                }
            })


            /*     editprof.getCandByEmail(mAuth.currentUser!!.email!!).observe(this@CandidateProfileAct, object : android.arch.lifecycle.Observer<Candidate> {
                     override fun onChanged(t: Candidate?) {
                         if (t != null) {
                             editprof.updateCand(t._id, CandidateProfile!!)!!.observe(this@CandidateProfileAct, object : android.arch.lifecycle.Observer<Candidate> {
                                 override fun onChanged(t: Candidate?) {
                                     Toast.makeText(applicationContext, "Profile edit with sucess", Toast.LENGTH_SHORT).show()
                                 }

                             })
                         }

                     }

                 })

              */
        }

        list = ArrayList()
        list.add("none")
        list.add("Beginner")
        list.add("Intermediere")
        list.add("Advanced")
        adapter = ArrayAdapter<String>(applicationContext,
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

    }
}
