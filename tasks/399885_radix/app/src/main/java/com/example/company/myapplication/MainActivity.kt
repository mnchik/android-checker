package com.example.company.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.BigInteger
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = findViewById<EditText>(R.id.number)
        val system = findViewById<EditText>(R.id.systemOfCalculus)
        val result = findViewById<TextView>(R.id.convertResult)
        findViewById<Button>(R.id.convertButton).setOnClickListener {
            val n = number.text.toString().toBigIntegerOrNull()
            val r = system.text.toString().toIntOrNull()
            result.text = if (n == null || r == null || r < 2 || r > 36) "Error" else n.toString(r)
        }
    }
}
