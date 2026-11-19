package com.example.company.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val path = findViewById<EditText>(R.id.path)
        val result = findViewById<TextView>(R.id.result)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        findViewById<Button>(R.id.perform).setOnClickListener {
            val dir = File(path.text.toString())
            val files = if (dir.isDirectory) dir.listFiles() else null
            result.text = if (files == null) "Error" else files.count { it.isFile }.toString()
        }
    }
}
