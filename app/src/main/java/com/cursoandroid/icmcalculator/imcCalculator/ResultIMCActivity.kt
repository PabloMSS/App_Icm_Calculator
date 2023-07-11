package com.cursoandroid.icmcalculator.imcCalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.cursoandroid.androidmaster.imcCalculator.ImcCalculatorActivity
import com.cursoandroid.icmcalculator.R

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        initComponent()
        initListener()
    }

    private fun initComponent(){
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListener(){
        var resultIMC = intent.extras?.get("resultIMC").toString() ?: "Error"
        var resultIMCFInal = resultIMC.toDouble()
        iniUI(resultIMCFInal)

        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun iniUI(result: Double){
        tvIMC.setText(result.toString())
        when(result){
            in 0.0 .. 18.50 -> {
                tvResult.text = getString(R.string.title_peso_bajo)
                tvDescription.text = getString(R.string.descripcion_peso_bajo)
            }
            in 18.51 .. 24.99 -> {
                tvResult.text = getString(R.string.title_peso_normal)
                tvDescription.text = getString(R.string.descripcion_peso_normal)
            }
            in 25.00 .. 29.99 -> {
                tvResult.text = getString(R.string.title_sobrepeso)
                tvDescription.text = getString(R.string.descripcion_sobrepeso)
            }
            in 30.00 .. 99.00 -> {
                tvResult.text = getString(R.string.title_obesidad)
                tvDescription.text = getString(R.string.descripcion_obesidad)
            }
            else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

}