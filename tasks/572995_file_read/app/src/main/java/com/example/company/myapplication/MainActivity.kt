package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filepath = findViewById<EditText>(R.id.filepath)
        val result = findViewById<TextView>(R.id.result)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        findViewById<Button>(R.id.perform).setOnClickListener {
            val file = File(Environment.getExternalStorageDirectory(), filepath.text.toString())
            result.text = try { file.readText() } catch (e: Exception) { "error" }
        }
    }
}
