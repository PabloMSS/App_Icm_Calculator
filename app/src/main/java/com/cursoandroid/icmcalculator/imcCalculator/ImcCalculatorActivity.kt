package com.cursoandroid.androidmaster.imcCalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.cursoandroid.icmcalculator.R
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    //Variables Globales
    private lateinit var viewMale:CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight : TextView
    private lateinit var rsHeight : RangeSlider

    private var isMaleSelected = true
    private var isFemaleSelected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListener()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
    }

    private fun initListener() {
        var contMale = 0
        var contFemale = 0
        viewMale.setOnClickListener {
            contMale++
            if(contMale == 1){
                changeGender()
                setGenderColor()
                contFemale = 0
            }
        }
        viewFemale.setOnClickListener {
            contFemale++
            if(contFemale == 1){
                changeGender()
                setGenderColor()
                contMale = 0
            }
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            tvHeight.setText(value.toInt().toString()+" cm")
        }
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponente:Boolean): Int {
        val colorReference = if (isSelectedComponente) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }
}