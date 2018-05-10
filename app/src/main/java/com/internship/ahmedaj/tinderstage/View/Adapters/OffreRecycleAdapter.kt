package com.internship.ahmedaj.tinderstage.View.Adapters

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.OffreItem
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.ChatFrag
import com.internship.ahmedaj.tinderstage.ViewModel.CandidateViewModel
import kotlinx.android.synthetic.main.offreitem.*
import java.util.ArrayList

/**
 * Created by ahmed aj on 26/04/2018.
 */

class OffreRecycleAdapter (var  ListOffre:List<OffreItem>,var context:Context,var view:View) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var recyclerView:RecyclerView=view.findViewById(R.id.recycleView)
        var lin:LinearLayout=view.findViewById(R.id.linOfferDetail)
        var offreitem=ListOffre.get((position))

        (holder as OffreViewHolder).binddata(offreitem)
        (holder as OffreViewHolder).linierlayout.setOnClickListener {

            Log.d("you click","item num"+position)

            Toast.makeText(context, "loading detail"+position,
                    Toast.LENGTH_SHORT).show()
            recyclerView.visibility=View.GONE
            lin.visibility=View.VISIBLE


        }
        bt_edit.setOnClickListener {
            Log.d("you click","item num"+position)

            Toast.makeText(context, "edit "+position,
                    Toast.LENGTH_SHORT).show()

        }

        bt_info.setOnClickListener {
            Log.d("you click","item num"+position)

            Toast.makeText(context, "info "+position,
                    Toast.LENGTH_SHORT).show()

        }
    }
    lateinit var bt_info:ImageButton
  lateinit  var bt_edit:ImageButton
    lateinit var layoutInflate: LayoutInflater
    private lateinit var chatFrag: ChatFrag
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        layoutInflate= LayoutInflater.from(parent.context)
        var view=LayoutInflater.from(parent?.context).inflate(R.layout.itemoffre2,parent,false)
        bt_info =view.findViewById(R.id.info_button)
        bt_edit=view.findViewById(R.id.edit_button)
        var ss: com.githang.stepview.StepView=view.findViewById(R.id.step_view1)
var candidateViewMode:CandidateViewModel
        candidateViewMode=
        return OffreViewHolder(view)
}

    override fun getItemCount(): Int {
        return ListOffre.size
    }
/*
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var recyclerView:RecyclerView=view.findViewById(R.id.recycleView)
        var lin:LinearLayout=view.findViewById(R.id.linOfferDetail)
        var offreitem=ListOffre.get((position))
        (holder as OffreViewHolder).binddata(offreitem)
        (holder as OffreViewHolder).linierlayout.setOnClickListener {

            Log.d("you click","item num"+position)

            Toast.makeText(context, "loading detail"+position,
                    Toast.LENGTH_SHORT).show()
            recyclerView.visibility=View.GONE
            lin.visibility=View.VISIBLE


        }

    }
*/
}