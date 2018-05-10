package com.internship.ahmedaj.tinderstage.View.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.internship.ahmedaj.tinderstage.Service.Model.OffreItem
import kotlinx.android.synthetic.main.itemoffre2.view.*
import java.util.ArrayList

/**
 * Created by ahmed aj on 26/04/2018.
 */
class OffreViewHolder(view: View): RecyclerView.ViewHolder(view){
 var entrepriseName:TextView=view.tx_titreOffreList
     var titleJob:TextView=view.tx_Info
    var ss: com.githang.stepview.StepView=view.step_view1
    lateinit var logoEntreprise:ImageView
    var linierlayout:LinearLayout=view.itemLayout
    fun binddata(offreItem:OffreItem){
        var ste = ArrayList<String>()
        entrepriseName.text=offreItem.entrepriseName
        titleJob.text=offreItem.jobTitle
        if(offreItem.etat.equals("")){


            ste.add("null")
            ste.add("null")
            ste.add("null")
            ss.setSteps(ste)
        }
      if(offreItem.etat.equals("Accepted")){
          ste.add("Applied")
        ste.add("Accepted")
        ste.add("Appruved")
        ss.setSteps(ste)
          ss.selectedStep(ss.currentStep+1)
      }
      else if(offreItem.etat.equals("Rejected")){
          ste.add("Applied")
          ste.add("Rejected")
          ss.setSteps(ste)
          ss.selectedStep(ss.currentStep+1)
      }else if(offreItem.etat.equals("Applied")){
          ste.add("Applied")
          ste.add("Accepted")
          ste.add("Appruved")
          ss.setSteps(ste)
      }

    }
}