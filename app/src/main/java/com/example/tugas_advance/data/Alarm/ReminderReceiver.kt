package com.example.tugas_advance.data.Alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.tugas_advance.utils.NotificationKeys.RMNDR_NOTI_TITLE_KEY

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val scheduleNotificationService = context?.let { ReminderNotification(it) }
        val title : String = intent?.getStringExtra(RMNDR_NOTI_TITLE_KEY) ?: return
        scheduleNotificationService?.sendReminderNotification(title)
    }
}