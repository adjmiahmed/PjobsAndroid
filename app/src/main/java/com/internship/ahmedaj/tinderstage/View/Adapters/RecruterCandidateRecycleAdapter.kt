package com.internship.ahmedaj.tinderstage.View.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.internship.ahmedaj.tinderstage.R
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateItemList


class RecruterCandidateRecycleAdapter (var  ListCand:List<CandidateItemList>, var context: Context, var view: View): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var bt_info: ImageButton
    lateinit var bt_edit: ImageButton

    lateinit var layoutInflate: LayoutInflater
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        var lin: LinearLayout = view.findViewById(R.id.linOfferDetail)
        var rv_cand_list: RecyclerView = view.findViewById(R.id.Rv_CandProfile)
        var canditem = ListCand.get((position))
        (holder as RecruterCandidateViewHolder).binddata()

        (holder as RecruterCandidateViewHolder).linierlayout.setOnClickListener {

            Log.d("you click", "item num" + position)

            Toast.makeText(context, "loading detail" + position,
                    Toast.LENGTH_SHORT).show()
            recyclerView.visibility = View.GONE
            lin.visibility = View.VISIBLE
            rv_cand_list.visibility=View.GONE


        }
        bt_edit.setOnClickListener {
            Log.d("you click", "item num" + position)

            Toast.makeText(context, "edit " + position,
                    Toast.LENGTH_SHORT).show()

        }

        bt_info.setOnClickListener {
            Log.d("you click", "item num" + position)

            Toast.makeText(context, "info " + position,
                    Toast.LENGTH_SHORT).show()

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        layoutInflate = LayoutInflater.from(parent.context)
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.rect_cand_profile_item, parent, false)
        bt_info = view.findViewById(R.id.info_button)
        bt_edit = view.findViewById(R.id.edit_button)

        return RecruterCandidateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ListCand.size
    }
}
