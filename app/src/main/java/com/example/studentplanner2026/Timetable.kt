package com.example.studentplanner2026

data class Timetable (
    val id: Int = 0,
    val day: String,
    val subject: String,
    val startTime: String,
    val endTime: String,
    val venue: String
)