package com.cursoandroid.androidmaster.imcCalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.cursoandroid.icmcalculator.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    //Variables Globales
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    private var isMaleSelected = true
    private var isFemaleSelected = false
    private var currentWeight: Int = 60
    private var currentAge: Int = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListener()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListener() {
        var contMale = 0
        var contFemale = 0
        viewMale.setOnClickListener {
            contMale++
            if (contMale == 1) {
                changeGender()
                setGenderColor()
                contFemale = 0
            }
        }
        viewFemale.setOnClickListener {
            contFemale++
            if (contFemale == 1) {
                changeGender()
                setGenderColor()
                contMale = 0
            }
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            tvHeight.setText(value.toInt().toString() + " CM")
        }

        btnSubtractWeight.setOnClickListener {
            currentWeight--
            setWeight()
        }

        btnPlusWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }

        btnSubtractAge.setOnClickListener {
            currentAge--
            setAge()
        }

        btnPlusAge.setOnClickListener {
            currentAge++
            setAge()
        }

        btnCalculate.setOnClickListener {
            Toast.makeText(this, calculateIMC().toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponente: Boolean): Int {
        val colorReference = if (isSelectedComponente) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setWeight() {
        tvWeight.setText(currentWeight.toString())
    }

    private fun setAge() {
        tvAge.setText(currentAge.toString())
    }

    private fun calculateIMC(): Double {
        var weight = tvWeight.text.toString().toInt()
        var height = (((tvHeight.text.split(" ")[0].toInt()) * 2) / 100).toDouble()
        var resultIMC = weight / height
        return resultIMC
    }

    //Ejecuta los Métodos por Primera Vez
    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}