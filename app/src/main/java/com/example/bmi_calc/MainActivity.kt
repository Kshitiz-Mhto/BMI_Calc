package com.example.bmi_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wtText = findViewById<EditText>(R.id.etWt)
        val htText = findViewById<EditText>(R.id.etHt)
        val calcButton = findViewById<Button>(R.id.idButton)

        calcButton.setOnClickListener{
            val wt = wtText.text.toString()
            val ht = htText.text.toString()

            if (validateInputs(wt,ht)) {
                val bmi = wt.toDouble() / (Math.pow((ht.toFloat() / 100).toDouble(), 2.0))

                // get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toDouble()
                displayResult(bmi2Digits)
            }

        }

    }

    private fun validateInputs(wt:String?, ht:String?):Boolean{

        return when{
            wt.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            ht.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }

    }

    private fun displayResult(bmi: Double) {
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultMsg = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.etRange)

        resultIndex.text = bmi.toString()
        info.text = "Normal range is 18.5 to 24.9"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_wt
            }
            bmi in 18.50 .. 24.99 ->{
                resultText = "Healthy"
                color = R.color.normal_wt
            }
            bmi in 25.00 .. 29.99 ->{
                resultText = "OverWeight"
                color = R.color.over_wt
            }
            else -> {
                resultText = "Obese over"
                color = R.color.obese
            }
        }
        resultMsg.setTextColor(ContextCompat.getColor(this, color))
        resultMsg.text = resultText

    }
}