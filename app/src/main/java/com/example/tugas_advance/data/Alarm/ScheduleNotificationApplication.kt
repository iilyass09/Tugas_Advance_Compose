package com.example.tugas_advance.data.Alarm

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tugas_advance.utils.NotificationKeys.RMNDR_NOTI_CHNNL_ID
import com.example.tugas_advance.utils.NotificationKeys.RMNDR_NOTI_CHNNL_NAME

class ScheduleNotificationApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Cek apakah perangkat memiliki API level 26 atau lebih tinggi
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Jika ya, buat notifikasi channel
            createNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            RMNDR_NOTI_CHNNL_ID,
            RMNDR_NOTI_CHNNL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
