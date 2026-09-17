package com.example.studentplanner2026

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import java.text.SimpleDateFormat
import java.util.Locale

object TaskNotificationScheduler {

    private const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"

    fun scheduleTaskNotification(
        context: Context,
        taskId: Int,
        title: String,
        description: String,
        dueDate: String,
        dueTime: String
    ) {
        try {
            val dateTimeFormat = SimpleDateFormat(
                DATE_TIME_FORMAT,
                Locale.getDefault()
            )

            dateTimeFormat.isLenient = false

            val triggerTime = dateTimeFormat.parse(
                "$dueDate $dueTime"
            )?.time ?: return

            if (triggerTime <= System.currentTimeMillis()) {
                return
            }

            val intent = Intent(
                context,
                TaskNotificationReceiver::class.java
            ).apply {
                putExtra("taskId", taskId)
                putExtra("title", title)
                putExtra("description", description)
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                taskId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

            val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            android.util.Log.d(
                "TaskNotification",
                "Notification scheduled for: $dueDate $dueTime"
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (!alarmManager.canScheduleExactAlarms()) {
                    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                        data = android.net.Uri.parse("package:${context.packageName}")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                    return
                }
            }

            try {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cancelTaskNotification(
        context: Context,
        taskId: Int
    ) {
        val intent = Intent(
            context,
            TaskNotificationReceiver::class.java
        )

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
    }
}