package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    private fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        var i = 2L
        while (i * i <= n) {
            if (n % i == 0L) return false
            i++
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        findViewById<Button>(R.id.checkBtn).setOnClickListener {
            val n = editText.text.toString().toLongOrNull()
            textView.text = when {
                n == null -> "error"
                isPrime(n) -> "prime"
                else -> "not prime"
            }
        }
    }
}
