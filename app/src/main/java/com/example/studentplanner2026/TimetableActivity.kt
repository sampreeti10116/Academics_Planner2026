package com.example.studentplanner2026

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class TimetableActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerTimetable: RecyclerView
    private lateinit var btnAddTimetable: MaterialButton
    lateinit var btnBackTimetable : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timetable)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        btnBackTimetable=findViewById<MaterialButton>(R.id.btnBackTimetable)
        btnBackTimetable.setOnClickListener {
            finish()
        }

        dbHelper = DatabaseHelper(this)

        recyclerTimetable = findViewById(R.id.recyclerTimetable)
        btnAddTimetable = findViewById(R.id.btnAddTimetable)

        recyclerTimetable.layoutManager = LinearLayoutManager(this)

        btnAddTimetable.setOnClickListener {
            val intent = Intent(this, AddTimetableActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadTimetable()
    }

    private fun loadTimetable() {
        val timetableEntries = ArrayList<Timetable>()

        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery(
            """
            SELECT * FROM timetable
            ORDER BY
                CASE day
                    WHEN 'Monday' THEN 1
                    WHEN 'Tuesday' THEN 2
                    WHEN 'Wednesday' THEN 3
                    WHEN 'Thursday' THEN 4
                    WHEN 'Friday' THEN 5
                    WHEN 'Saturday' THEN 6
                    WHEN 'Sunday' THEN 7
                END,
                startTime ASC
            """.trimIndent(),
            null
        )

        while (cursor.moveToNext()) {
            val entry = Timetable(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                day = cursor.getString(cursor.getColumnIndexOrThrow("day")),
                subject = cursor.getString(cursor.getColumnIndexOrThrow("subject")),
                startTime = cursor.getString(cursor.getColumnIndexOrThrow("startTime")),
                endTime = cursor.getString(cursor.getColumnIndexOrThrow("endTime")),
                venue = cursor.getString(cursor.getColumnIndexOrThrow("venue"))
            )

            timetableEntries.add(entry)
        }

        cursor.close()
        db.close()

        recyclerTimetable.adapter = TimetableAdapter(timetableEntries) { entry ->
            deleteTimetableEntry(entry)
        }
    }

    private fun deleteTimetableEntry(entry: Timetable) {
        val db = dbHelper.writableDatabase

        db.delete(
            "timetable",
            "id = ?",
            arrayOf(entry.id.toString())
        )

        db.close()

        Toast.makeText(
            this,
            "Timetable entry deleted",
            Toast.LENGTH_SHORT
        ).show()

        loadTimetable()
    }
}