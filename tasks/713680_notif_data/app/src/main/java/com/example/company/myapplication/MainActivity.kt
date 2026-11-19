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
import android.widget.EditText
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
        val number = findViewById<EditText>(R.id.number)
        val message = findViewById<EditText>(R.id.message)
        findViewById<Button>(R.id.notify).setOnClickListener {
            val raw = number.text.toString()
            val num = if (raw.isEmpty()) "" else (raw.toBigIntegerOrNull()?.toString() ?: raw)
            val intent = Intent(this, FinishActivity::class.java)
            intent.putExtra("number", num)
            intent.putExtra("message", message.text.toString())
            val pi = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, "ch")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Title")
                .setContentText("Text")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(1, notification)
        }
    }
}
