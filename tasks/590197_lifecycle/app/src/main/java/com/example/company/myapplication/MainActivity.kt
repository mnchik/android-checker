package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    private var savedText: String? = null

    override fun onPause() {
        super.onPause()
        val editText = findViewById<EditText>(R.id.editText)
        savedText = editText.text.toString()
        editText.setText("")
    }

    override fun onResume() {
        super.onResume()
        savedText?.let { findViewById<TextView>(R.id.textView).text = it }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
