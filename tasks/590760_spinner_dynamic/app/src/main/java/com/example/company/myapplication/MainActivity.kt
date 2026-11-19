package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, ArrayList<Int>())
        spinner.adapter = adapter

        fun refill(text: String) {
            adapter.clear()
            val n = text.toIntOrNull()
            if (n != null && n > 0) {
                for (i in 1..n) adapter.add(i)
            }
            adapter.notifyDataSetChanged()
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { refill(s.toString()) }
        })
        refill(editText.text.toString())
    }
}
