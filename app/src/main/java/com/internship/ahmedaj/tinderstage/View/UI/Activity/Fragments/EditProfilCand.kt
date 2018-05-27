package com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.internship.ahmedaj.tinderstage.R


/**
 * A simple [Fragment] subclass.
 */
class EditProfilCand : Fragment() {
    lateinit var spin: Spinner
    lateinit var spin2: Spinner
    lateinit var spin3: Spinner

    lateinit var list:ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var view=inflater.inflate(R.layout.fragment_edit_profil_cand, container, false)

        spin=view.findViewById(R.id.sp_education2)
        spin2=view.findViewById(R.id.sp_education3)
        spin3=view.findViewById(R.id.sp_education4)

        list=ArrayList()
        list.add("none")
        list.add("Beginner")
        list.add("Intermediere")
        list.add("Advanced")
        adapter  = ArrayAdapter<String>(view.context,
                android.R.layout.simple_spinner_item, list)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }



            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        Log.d("none is slected","")
                    }
                    1->{
                        Log.d("Beginner","is slected")
                    }
                    2->{
                        Log.d("Intermediere","is slected")
                    }
                    3->{
                        Log.d("Advanced","is slected")
                    }

                }
            }
        });

        spin3.setAdapter(adapter);
        spin3.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }



            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        Log.d("none is slected","")
                    }
                    1->{
                        Log.d("Beginner","is slected")
                    }
                    2->{
                        Log.d("Intermediere","is slected")
                    }
                    3->{
                        Log.d("Advanced","is slected")
                    }

                }
            }
        })
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }



            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        Log.d("none is slected","")
                    }
                    1->{
                        Log.d("Beginner","is slected")
                    }
                    2->{
                        Log.d("Intermediere","is slected")
                    }
                    3->{
                        Log.d("Advanced","is slected")
                    }

                }
            }
        })


        return view

    }

}// Required empty public constructor
