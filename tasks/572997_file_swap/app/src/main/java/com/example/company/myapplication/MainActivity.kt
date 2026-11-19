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
        val filepath2 = findViewById<EditText>(R.id.filepath2)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        findViewById<Button>(R.id.perform).setOnClickListener {
            val root = Environment.getExternalStorageDirectory()
            val f1 = File(root, filepath.text.toString())
            val f2 = File(root, filepath2.text.toString())
            val ok1 = f1.isFile
            val ok2 = f2.isFile
            if (!ok1) filepath.setText("error")
            if (!ok2) filepath2.setText("error")
            if (ok1 && ok2) {
                val t1 = f1.readText()
                val t2 = f2.readText()
                f1.writeText(t2)
                f2.writeText(t1)
            }
        }
    }
}
