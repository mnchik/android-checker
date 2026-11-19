package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val rules = listOf("question" to "answer", "request" to "response", "problem" to "task")
        var replaced = 0
        var busy = false
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (busy || s == null) return
                busy = true
                var found = true
                while (found) {
                    found = false
                    for ((from, to) in rules) {
                        val i = s.indexOf(from)
                        if (i >= 0) {
                            s.replace(i, i + from.length, to)
                            replaced++
                            found = true
                            break
                        }
                    }
                }
                busy = false
                textView.text = replaced.toString()
            }
        })
    }
}
