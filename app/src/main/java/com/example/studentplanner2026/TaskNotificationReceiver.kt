package com.example.studentplanner2026

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TaskNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        val taskId = intent.getIntExtra("taskId", -1)
        val title = intent.getStringExtra("title") ?: "Upcoming Task"
        val description = intent.getStringExtra("description") ?: ""

        NotificationHelper.showTaskNotification(
            context,
            taskId,
            title,
            description
        )
        android.util.Log.d(
            "TaskNotification",
            "Receiver triggered for task: $taskId"
        )

    }

}