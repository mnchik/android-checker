package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, ArrayList<Int>())
        listView.adapter = adapter

        fun refill(n: Int) {
            adapter.clear()
            for (i in 1..n) adapter.add(i * i)
            adapter.notifyDataSetChanged()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, progress: Int, fromUser: Boolean) { refill(progress) }
            override fun onStartTrackingTouch(bar: SeekBar?) {}
            override fun onStopTrackingTouch(bar: SeekBar?) {}
        })
        refill(seekBar.progress)
    }
}
