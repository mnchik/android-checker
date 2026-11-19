package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_prev1).setOnClickListener { startActivity(Intent(this, ThirdActivity::class.java)) }
        findViewById<Button>(R.id.button_next1).setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }
}
