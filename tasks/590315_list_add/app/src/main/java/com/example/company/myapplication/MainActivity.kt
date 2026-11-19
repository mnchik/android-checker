package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ArrayList<String>((0..10).map { (it * it).toString() })
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            adapter.add(items[position])
        }
    }
}
