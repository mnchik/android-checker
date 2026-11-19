package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.ListView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = (1..10).map { it.toString() }
        val listView = findViewById<ListView>(R.id.listView1)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ListItemActivity::class.java)
            intent.putExtra("text", items[position])
            startActivity(intent)
        }
    }
}
