package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("text", editText.text.toString())
            startActivity(intent)
        }
    }
}
