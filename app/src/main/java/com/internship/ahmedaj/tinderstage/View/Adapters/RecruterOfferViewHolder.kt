package com.internship.ahmedaj.tinderstage.View.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.internship.ahmedaj.tinderstage.Service.Model.OfferItemWithId
import kotlinx.android.synthetic.main.recruter_offer_item.view.*

/**
 * Created by ahmed aj on 08/05/2018.
 */
class RecruterOfferViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    var linierlayout: LinearLayout = view.itemLayout1
    fun binddata(offreitem: OfferItemWithId) {
        view.tx_titreOffreList.text = offreitem.entrepriseName
        view.tx_Info.text = offreitem.jobTitle
        view.tx_dateOffre.text = offreitem.dateoffre.toString()
    }
}