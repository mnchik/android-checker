package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arg1 = findViewById<EditText>(R.id.arg1)
        val arg2 = findViewById<EditText>(R.id.arg2)
        val answer = findViewById<TextView>(R.id.answer)

        fun calc(op: (Int, Int) -> Int, isDivision: Boolean = false) {
            val a = arg1.text.toString().toIntOrNull()
            val b = arg2.text.toString().toIntOrNull()
            answer.text = when {
                a == null || b == null -> "Input Error"
                isDivision && b == 0 -> "Div by zero"
                else -> op(a, b).toString()
            }
        }

        findViewById<Button>(R.id.add).setOnClickListener { calc({ a, b -> a + b }) }
        findViewById<Button>(R.id.subtr).setOnClickListener { calc({ a, b -> a - b }) }
        findViewById<Button>(R.id.mul).setOnClickListener { calc({ a, b -> a * b }) }
        findViewById<Button>(R.id.divide).setOnClickListener { calc({ a, b -> a / b }, true) }
    }
}
