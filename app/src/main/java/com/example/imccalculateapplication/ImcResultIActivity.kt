package com.example.imccalculateapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat


class ImcResultIActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result_iactivity)
        val result: Double = intent.extras?.getDouble("cualquierCosa") ?: -1.0
        initComponent()
        initUi(result)
        initListeners()

    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUi(result: Double) {
        tvImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {//bajo peso
                tvResult.text = getString(R.string.tittle_low_wheight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_low_weight)
            }
            in 18.51..24.99 -> {//peso normal
                tvResult.text = getString(R.string.tittle_normal_wheight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.pero_normal))
                tvDescription.text = getString(R.string.description_normal_weight)
            }
            in 25.00..29.99 -> {//obeso
                tvResult.text = getString(R.string.tittle_hihgt_wheight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                tvDescription.text = getString(R.string.description_hihgt_weight)
            }
            in 30.00..99.99 -> {//obesidad morbida
                tvResult.text = getString(R.string.tittle_rehihgt_wheight)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text = getString(R.string.description_rehihgt_weight)
            }
            else -> {
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        tvResult = findViewById(R.id.tvResult)
        tvImc = findViewById(R.id.tvImc)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }

}