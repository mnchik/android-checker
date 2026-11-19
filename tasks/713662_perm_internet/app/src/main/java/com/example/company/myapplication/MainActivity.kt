package com.example.company.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        findViewById<Button>(R.id.button).setOnClickListener {
            val address = editText.text.toString()
            Thread {
                val ok = try {
                    val conn = URL(address).openConnection() as HttpURLConnection
                    conn.connectTimeout = 5000
                    conn.readTimeout = 5000
                    val code = conn.responseCode
                    conn.disconnect()
                    code != 404 && code != 500
                } catch (e: Exception) {
                    false
                }
                runOnUiThread { textView.text = if (ok) "Ok" else "Failed" }
            }.start()
        }
    }
}
