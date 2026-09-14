package com.example.studentplanner2026

data class Exam(
    val id: Int = 0,
    val subject: String,
    val examDate: String,
    val examTime: String,
    val venue: String
)