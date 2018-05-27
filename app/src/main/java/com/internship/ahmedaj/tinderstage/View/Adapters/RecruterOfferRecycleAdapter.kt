package com.internship.ahmedaj.tinderstage.View.Adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import com.internship.ahmedaj.tinderstage.Service.Model.OfferItemWithId
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.EditOffer
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.InfoOffer
import com.internship.ahmedaj.tinderstage.View.UI.Activity.Fragments.ProfileInOffer
import java.util.*


/**
 * Created by ahmed aj on 08/05/2018.
 */
class RecruterOfferRecycleAdapter(var ListOffre: List<OfferItemWithId>, var context: Context, var view: View) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var recyCandidateProfile: RecyclerView
    val activity = view.context as AppCompatActivity
    var editOffer = EditOffer()
    var infoOffer=InfoOffer()
var profileInOffer=ProfileInOffer()
    var listCand = ArrayList<CandidateItemList>()
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        var lin: LinearLayout = view.findViewById(R.id.linOfferDetail)
        recyCandidateProfile = view.findViewById(R.id.Rv_CandProfile)
        var offreitem = ListOffre.get((position))
        (holder as RecruterOfferViewHolder).binddata(offreitem)
        (holder as RecruterOfferViewHolder).linierlayout.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("OfferId", offreitem.id);
            profileInOffer.setArguments(bundle);
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainlayoutApp, profileInOffer).addToBackStack(null).commit()


            /*
            Log.d("you click","item num"+position)
            var listCand=ArrayList<CandidateItemList>()
            for(i in 1..5){listCand.add(CandidateItemList("","", Date(),""))}

            Toast.makeText(context, "loading detail"+position,
                    Toast.LENGTH_SHORT).show()
            recyCandidateProfile.setHasFixedSize(true)
            recyCandidateProfile.setLayoutManager(LinearLayoutManager(view.context));
            val adapterCandidateProfile= RecruterCandidateRecycleAdapter(listCand,view.context,view)
            recyCandidateProfile.adapter=adapterCandidateProfile
            recyclerView.visibility=View.GONE
            lin.visibility=View.GONE
            recyCandidateProfile.visibility=View.VISIBLE
*/
        }
        bt_edit.setOnClickListener {
            Log.d("you click", "item num" + position)
            val bundle = Bundle()
            bundle.putString("OfferId", offreitem.id);
            editOffer.setArguments(bundle);
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainlayoutApp, editOffer).addToBackStack(null).commit()

            Toast.makeText(context, "edit " + position,
                    Toast.LENGTH_SHORT).show()


        }

        bt_info.setOnClickListener {
            Log.d("you click", "item num" + position)
            val bundle = Bundle()
            bundle.putString("OfferId", offreitem.id);
            infoOffer.setArguments(bundle);
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainlayoutApp, infoOffer).addToBackStack(null).commit()

            Toast.makeText(context, "info " + position,
                    Toast.LENGTH_SHORT).show()

        }


    }

    lateinit var bt_info: ImageButton
    lateinit var bt_edit: ImageButton
    lateinit var layoutInflate: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        layoutInflate = LayoutInflater.from(parent.context)
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.recruter_offer_item, parent, false)
        bt_info = view.findViewById(R.id.info_button)
        bt_edit = view.findViewById(R.id.edit_button)

        return RecruterOfferViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ListOffre.size
    }

}