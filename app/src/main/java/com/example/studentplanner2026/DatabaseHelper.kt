package com.example.studentplanner2026

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "AcademicPlanner.db", null, 4) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("""
            CREATE TABLE subjects (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                code TEXT
            )
        """.trimIndent())

        db.execSQL(""" 
            CREATE TABLE tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                dueDate TEXT,
                priority TEXT,
                isCompleted INTEGER
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE exams (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                subject TEXT,
                examDate TEXT,
                examTime TEXT,
                venue TEXT
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE timetable (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                day TEXT,
                subject TEXT,
                startTime TEXT,
                endTime TEXT,
                venue TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (oldVersion < 2) {
            db.execSQL(
                """
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                dueDate TEXT,
                priority TEXT,
                isCompleted INTEGER DEFAULT 0
            )
            """.trimIndent()
            )
        }

        if (oldVersion < 3) {
            db.execSQL("""
        CREATE TABLE IF NOT EXISTS exams (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            subject TEXT,
            examDate TEXT,
            examTime TEXT,
            venue TEXT
        )
    """.trimIndent())
        }

        if (oldVersion < 4) {
            db.execSQL("""
        CREATE TABLE IF NOT EXISTS timetable (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            day TEXT,
            subject TEXT,
            startTime TEXT,
            endTime TEXT,
            venue TEXT
        )
    """.trimIndent())
        }
    }
}