package com.internship.ahmedaj.tinderstage.View.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.internship.ahmedaj.tinderstage.Service.Model.Candidat.CandidateItemList
import kotlinx.android.synthetic.main.rect_cand_profile_item.view.*


/**
 * Created by ahmed aj on 08/05/2018.
 */
class RecruterCandidateViewHolder(var view:View): RecyclerView.ViewHolder(view) {

    var linierlayout:LinearLayout=view.itemLayoutCand
    fun binddata(canditem: CandidateItemList) {
       view.tx_candname_item.text=canditem.name
        view.tx_summary.text=canditem.summary
        view.tx_dateApplication.text=canditem.date_app.toString()
    }
}