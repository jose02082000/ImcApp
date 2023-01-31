package com.example.imccalculateapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat



class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 50
    private var currentAge: Int = 20
    private var currentHeight: Int = 100

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsheight: RangeSlider
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalculate: Button

   /** companion object {
        const val IMC_Key = "imc_result"
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListener()
        initUi()

    }

    private fun initComponent() {
        viewMale = findViewById(R.id.ViewMale)
        viewFemale = findViewById(R.id.ViewFemale)
        tvHeight = findViewById(R.id.TvHeight)
        rsheight = findViewById(R.id.rsHeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsheight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight(currentWeight)
        }
        btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight(currentWeight)
        }
        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge(currentAge)
        }
        btnAddAge.setOnClickListener {
            currentAge += 1
            setAge(currentAge)
        }
        btnCalculate.setOnClickListener {
            val result = calculateImc()
            navigateToResult(result)

        }

    }

    private fun navigateToResult(nameGeneric:Double){
        val intent = Intent(this, ImcResultIActivity::class.java)
        intent.putExtra("cualquierCosa", nameGeneric)
        startActivity(intent)
    }

    private fun calculateImc():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / ((currentHeight.toDouble()/100) * (currentHeight.toDouble()/100))
        return df.format(imc).toDouble()

       // Log.i("cualquierCosa","Tú IMC es $result") para ver en el log
    }

    private fun setAge(nameRandom: Int) {
        tvAge.text = nameRandom.toString()
    }

    private fun setWeight(nameRandom: Int) {
        tvWeight.text = nameRandom.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(
            getBackgroundColor(isMaleSelected)
        )
        viewFemale.setCardBackgroundColor(
            getBackgroundColor(isFemaleSelected)
        )

    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUi() {
        setGenderColor()
        setWeight(currentWeight)
        setAge(currentAge)
        calculateImc()
    }


}