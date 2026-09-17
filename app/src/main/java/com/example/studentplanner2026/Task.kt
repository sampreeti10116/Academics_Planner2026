package com.example.studentplanner2026

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: String,
    val dueTime: String,
    val priority: String,
    val isCompleted: Boolean = false
)