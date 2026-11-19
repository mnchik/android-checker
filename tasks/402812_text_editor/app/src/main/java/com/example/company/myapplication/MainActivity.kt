package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val stats = findViewById<TextView>(R.id.stats_view)
        val unsaved = findViewById<TextView>(R.id.unsaved_changes_view)
        val wordRegex = Regex("[A-Za-zА-Яа-яЁё_]+")
        var saved = ""

        fun refresh() {
            val text = editText.text.toString()
            stats.text = wordRegex.findAll(text).count().toString()
            unsaved.text = if (text == saved) "All changes saved" else "Unsaved changes"
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { refresh() }
        })
        findViewById<Button>(R.id.save_button).setOnClickListener { saved = editText.text.toString(); refresh() }
        findViewById<Button>(R.id.load_button).setOnClickListener { editText.setText(saved); refresh() }
        findViewById<Button>(R.id.clear_button).setOnClickListener { editText.setText(""); refresh() }
        refresh()
    }
}
