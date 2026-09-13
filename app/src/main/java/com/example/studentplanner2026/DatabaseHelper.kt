package com.example.studentplanner2026

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "AcademicPlanner.db", null, 2) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE subjects (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                code TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
    CREATE TABLE tasks (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT,
        description TEXT,
        dueDate TEXT,
        priority TEXT,
        isCompleted INTEGER
    )
    """.trimIndent()
        )
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
    }
}