package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView?=null
    var lastnumeric: Boolean = false
    var lastdot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastnumeric = true
        lastdot= false

    }
    fun clear(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if (lastnumeric && !lastdot){
            tvInput?.append(".")
            lastnumeric = false
            lastdot = true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastnumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false

            }
        }
    }

    fun onEqual(view: View){
        if(lastnumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }else   if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }else    if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }else    if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZero (result: String):String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if (value.startsWith("")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }
}