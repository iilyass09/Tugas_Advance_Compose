package com.example.tugas_advance.data.Alarm

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.example.tugas_advance.R
import com.example.tugas_advance.utils.NotificationKeys.RMNDR_NOTI_CHNNL_ID
import com.example.tugas_advance.utils.NotificationKeys.RMNDR_NOTI_ID

class ReminderNotification(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun sendReminderNotification(title: String?){
        val notification = NotificationCompat.Builder(context, RMNDR_NOTI_CHNNL_ID)
            .setContentTitle(title)
            .setContentText(context.getString(R.string.app_name))
            .setSmallIcon(R.drawable.notif_small)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.notif_small
                )
            )
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(context.bitmapFromResource(R.drawable.logo))
            )
            .setAutoCancel(true)
            .build()
        notificationManager.notify(RMNDR_NOTI_ID, notification)
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}