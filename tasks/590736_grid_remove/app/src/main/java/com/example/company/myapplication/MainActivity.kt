package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.GridView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ArrayList<String>((0..30).map { it.toString() })
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        val grid = findViewById<GridView>(R.id.gridView)
        grid.adapter = adapter
        grid.setOnItemClickListener { _, _, position, _ ->
            adapter.remove(adapter.getItem(position))
        }
    }
}
