package com.example.company.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(NotificationChannel("ch", "Channel", NotificationManager.IMPORTANCE_DEFAULT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ensureChannel()
        findViewById<Button>(R.id.notify).setOnClickListener {
            val notification = NotificationCompat.Builder(this, "ch")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Title")
                .setContentText("Text")
                .build()
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(1, notification)
        }
    }
}
