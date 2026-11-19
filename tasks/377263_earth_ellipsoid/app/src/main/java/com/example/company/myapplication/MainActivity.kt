package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rg = findViewById<RadioGroup>(R.id.rgAnswers)
        val tvAnswer = findViewById<TextView>(R.id.tvAnswer)
        findViewById<Button>(R.id.butAnswer).setOnClickListener {
            tvAnswer.text = if (rg.checkedRadioButtonId == R.id.rbEllipsoid) "Правильно!" else "Неправильно!"
        }
    }
}
