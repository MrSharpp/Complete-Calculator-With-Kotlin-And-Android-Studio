package com.example.simplecalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.one).setOnClickListener { appendOnClick("1") }
        findViewById<Button>(R.id.two).setOnClickListener { appendOnClick("2") }
        findViewById<Button>(R.id.three).setOnClickListener { appendOnClick("3") }
        findViewById<Button>(R.id.four).setOnClickListener { appendOnClick("4") }
        findViewById<Button>(R.id.five).setOnClickListener { appendOnClick("5") }
        findViewById<Button>(R.id.six).setOnClickListener { appendOnClick("6") }
        findViewById<Button>(R.id.seven).setOnClickListener { appendOnClick("7") }
        findViewById<Button>(R.id.eight).setOnClickListener { appendOnClick("8") }
        findViewById<Button>(R.id.nine).setOnClickListener { appendOnClick("9") }
        findViewById<Button>(R.id.zero).setOnClickListener { appendOnClick("0") }
        findViewById<Button>(R.id.multiply).setOnClickListener { appendOnClick("*") }
        findViewById<Button>(R.id.divide).setOnClickListener { appendOnClick("/") }
        findViewById<Button>(R.id.subtract).setOnClickListener { appendOnClick("-") }
        findViewById<Button>(R.id.add).setOnClickListener { appendOnClick("+") }
        findViewById<Button>(R.id.result).setOnClickListener { calculate() }
        findViewById<Button>(R.id.clearButton).setOnClickListener { clear() }
        findViewById<Button>(R.id.backspace).setOnClickListener { backspace() }
        findViewById<Button>(R.id.point).setOnClickListener { appendOnClick( ".") }
        
    }

    private fun appendOnClick(string: String) {
        var history = findViewById<TextView>(R.id.history).text.toString()
        var verify = history.takeLast(1)
        if(string == "+" || string == "-" || string == "/" || string == "." || string == "*") if(verify == "+" || verify == "-" || verify == "/" || verify == "." || verify == "*") return
        if(findViewById<TextView>(R.id.history).text.toString() == "0" && string != "+" && string != "-" && string != "/" && string != "." && string != "*") findViewById<TextView>(R.id.history).setText(string)
        else findViewById<TextView>(R.id.history).append(string)

    }

    private fun calculate(){
        var string = findViewById<TextView>(R.id.history).text.toString()
        var verify = string.takeLast(1)
        if(verify == "+" || verify == "-" || verify == "/" || verify == "." || verify == "*") string = string.dropLast(1)
        try {
            val input = ExpressionBuilder(string).build()
            val output = input.evaluate().toString()
            var string: Long? = null
            var trail = output.split('.').toTypedArray()

            println("Text:" + trail[1])

             if (trail[1].toInt() > 0) string = output.toLong()
             else string = trail[0].toLong()


                findViewById<TextView>(R.id.history).setText(string.toString())
        }catch (e:Exception){
            Toast.makeText(this, "Error:" + e.message + ", OnLine:" + e.cause , Toast.LENGTH_LONG).show()
        }
    }

    private fun clear(){
        findViewById<TextView>(R.id.history).setText("0")
    }

    private fun backspace(){
        if(findViewById<TextView>(R.id.history).text.toString().length == 1) return findViewById<TextView>(R.id.history).setText("0")
        println("Type:" + findViewById<TextView>(R.id.history).text.toString()::class.simpleName)
        findViewById<TextView>(R.id.history).setText(findViewById<TextView>(R.id.history).text.dropLast(1))
    }


}