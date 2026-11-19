package com.example.company.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        findViewById<TextView>(R.id.number).text = intent.getStringExtra("number") ?: ""
        findViewById<TextView>(R.id.message).text = intent.getStringExtra("message") ?: ""
    }
}
