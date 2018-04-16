package com.internship.ahmedaj.tinderstage

import android.content.Context
import android.content.res.AssetManager
import android.util.Log

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.json.JSONArray

import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

/**
 * Created by ahmed aj on 23/03/2018.
 */
object Utils {

    private val TAG = "Utils"

    fun loadProfiles(context: Context): List<Profile>? {
        try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val array = JSONArray(loadJSONFromAsset(context, "profiles.json"))
            val profileList = ArrayList<Profile>()
            for (i in 0 until array.length()) {
                val profile = gson.fromJson(array.getString(i), Profile::class.java)
                profileList.add(profile)
            }
            return profileList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
        var json: String? = null
        var `is`: InputStream? = null
        try {
            val manager = context.assets
            Log.d(TAG, "path $jsonFileName")
            `is` = manager.open(jsonFileName)
            val size = `is`!!.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}