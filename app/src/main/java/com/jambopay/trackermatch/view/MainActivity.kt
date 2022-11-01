package com.jambopay.trackermatch.view

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jambopay.trackermatch.R
import com.jambopay.trackermatch.model.Model
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var cartList: ArrayList<Model>? = null

    // will return an `Int` between 0 and 10 included
    // to increase add the number to the value you want to be maximum as well as minimum
    private val maxNumber = 10
    private val minNumber = 0

    //Shared Preference name Key
    private val keySharedPreference = "data_stored"

    private val keyLastMatchMilliSeconds = "last_match_in_milliseconds"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var milliSeconds: Long = 0

        val sharedPreference =  getSharedPreferences(keySharedPreference, Context.MODE_PRIVATE)
        var longLastMatch: Long = sharedPreference.getLong(keyLastMatchMilliSeconds, milliSeconds)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val json: String? = prefs.getString("matchList", null)

        Log.e(TAG, "JSON: $json")

        //START
        cartList = ArrayList()

        if (json != null) {

            cartList = getArrayList("matchList",this@MainActivity)

            Log.e(TAG, "ALL LIST: $cartList")
        }

        //END

        btnSubmitInput.setOnClickListener {

            if(checkInputValue()) {
                //if input field check returns true, the function below is called
                randomGeneratorButtonClicked(longLastMatch)
            }

        }

    }

    private fun randomGeneratorButtonClicked( longLastMatch:Long) {

        //Generates a random number in the set minimum and maximum limits
        val randomNumber = (minNumber..maxNumber).random()

        // Calculates the difference
        val difference = maxNumber - etNumberInput.text.toString() .toInt()

        // Safety confirm to ensure the value is within the maximum and minimum ranges set
        if(difference >= 0 ) {

            val firstMatchTime = System.currentTimeMillis()

            if (longLastMatch == 0L){

                var textDisplayMilliseconds = "Time in Milliseconds first match"

                tvTextAboutMilliseconds.text = textDisplayMilliseconds
                tvMillisecondsDifference.text = firstMatchTime.toString()

            } else {

                var textDisplayMilliseconds = "Time in milliseconds between the last match"

                val secondMatchTime = longLastMatch - firstMatchTime

                tvTextAboutMilliseconds.text = textDisplayMilliseconds
                tvMillisecondsDifference.text = secondMatchTime.toString()

            }

            val sharedPreference =  getSharedPreferences(keySharedPreference,Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putLong(keyLastMatchMilliSeconds,firstMatchTime)
            editor.commit()

 //           Log.e(ContentValues.TAG, "onCreate: Millisecond differences $firstMatchTime")

            val inputNumberValue = etNumberInput.text.toString()
            val randomNumberValue = randomNumber.toString()


            tvInputNumber.text = inputNumberValue
            tvGeneratedRandomNumber.text = randomNumberValue

            if(randomNumberValue == inputNumberValue) {
                //green
                tvGeneratedRandomNumber.setBackgroundColor(Color.parseColor("#4CAF50"))
            }else {
                //red
                tvGeneratedRandomNumber.setBackgroundColor(Color.parseColor("#F40404"))
            }

            if (cartList != null ) {

                val trials = cartList?.size

                tvTrials.text = "Total match attempts: $trials"


            }

            cartList?.add(Model(inputNumberValue,randomNumberValue,firstMatchTime.toString(),keyLastMatchMilliSeconds))
            saveArrayList(cartList,"matchList",this@MainActivity)


        } else {
            Toast.makeText(this@MainActivity,"FALSE", Toast.LENGTH_LONG).show()

            tvInputNumber.text = ""
            tvGeneratedRandomNumber.text = ""

        }

    }

    // Checks value from the input field
    private fun checkInputValue(): Boolean {
        if (etNumberInput.text.toString() == "") {
            etNumberInput.error = "Kindly input a random number between $minNumber and $maxNumber."
            etNumberInput.requestFocus()
            return false
        }
        if (etNumberInput.text.toString() .toInt() > maxNumber) {
            etNumberInput.error = "Kindly input a number less than the $maxNumber."
            etNumberInput.requestFocus()
            return false
        }
        return true
    }

    //Save and retrieve Arraylist
    fun saveArrayList(list: ArrayList<Model>?, key: String?, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?, context: Context): ArrayList<Model>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString(key, null)
        val type = object : TypeToken<java.util.ArrayList<Model?>?>() {}.type
        return gson.fromJson(json, type)
    }

}