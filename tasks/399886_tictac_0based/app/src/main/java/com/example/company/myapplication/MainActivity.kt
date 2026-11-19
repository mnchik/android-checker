package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.status)
        val spinners = listOf(R.id.spinner00, R.id.spinner01, R.id.spinner02, R.id.spinner10, R.id.spinner11, R.id.spinner12, R.id.spinner20, R.id.spinner21, R.id.spinner22).map { findViewById<Spinner>(it) }
        val values = listOf("", "0", "X")

        fun analyze() {
            val cells = spinners.map { values[it.selectedItemPosition] }
            val x = cells.count { it == "X" }
            val o = cells.count { it == "0" }
            val lines = listOf(listOf(0,1,2), listOf(3,4,5), listOf(6,7,8), listOf(0,3,6), listOf(1,4,7), listOf(2,5,8), listOf(0,4,8), listOf(2,4,6))
            fun wins(p: String) = lines.any { l -> l.all { cells[it] == p } }
            val xw = wins("X")
            val ow = wins("0")
            status.text = when {
                Math.abs(x - o) > 1 -> "Invalid"
                xw && ow -> "Invalid"
                xw -> "X won"
                ow -> "0 won"
                x + o == 9 -> "Draw"
                else -> ""
            }
        }

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { analyze() }
            override fun onNothingSelected(parent: AdapterView<*>?) { analyze() }
        }
        for (s in spinners) {
            s.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, values)
            s.onItemSelectedListener = listener
        }
        analyze()
    }
}
