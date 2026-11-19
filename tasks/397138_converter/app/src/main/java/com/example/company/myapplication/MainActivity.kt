package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val status = findViewById<TextView>(R.id.status)
        val k = 39370f
        var updating = false

        fun watch(src: EditText, dst: EditText, convert: (Float) -> Float) {
            src.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (updating) return
                    val v = s.toString().toFloatOrNull()
                    if (v == null) {
                        status.text = "error"
                    } else {
                        updating = true
                        dst.setText(convert(v).toString())
                        updating = false
                        status.text = ""
                    }
                }
            })
        }
        watch(editText, editText2) { it / k }
        watch(editText2, editText) { it * k }
    }
}
