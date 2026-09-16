package com.example.studentplanner2026

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AddTimetableActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var dayAutoComplete: MaterialAutoCompleteTextView
    private lateinit var subjectEditText: TextInputEditText
    private lateinit var startTimeEditText: TextInputEditText
    private lateinit var endTimeEditText: TextInputEditText
    private lateinit var venueEditText: TextInputEditText
    private lateinit var saveButton: MaterialButton
    lateinit var btnBackAddClass : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_timetable)

        dbHelper = DatabaseHelper(this)

        dayAutoComplete = findViewById(R.id.actvTimetableDay)
        subjectEditText = findViewById(R.id.etTimetableSubject)
        startTimeEditText = findViewById(R.id.etTimetableStartTime)
        endTimeEditText = findViewById(R.id.etTimetableEndTime)
        venueEditText = findViewById(R.id.etTimetableVenue)
        saveButton = findViewById(R.id.btnSaveTimetable)

        val days = arrayOf(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )

        val dayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            days
        )

        btnBackAddClass=findViewById<MaterialButton>(R.id.btnBackAddClass)
        btnBackAddClass.setOnClickListener {
            val intent= Intent(this, TimetableActivity::class.java)
            startActivity(intent)
        }

        dayAutoComplete.setAdapter(dayAdapter)

        dayAutoComplete.setOnClickListener {
            dayAutoComplete.showDropDown()
        }

        startTimeEditText.setOnClickListener {
            showTimePicker(startTimeEditText)
        }

        endTimeEditText.setOnClickListener {
            showTimePicker(endTimeEditText)
        }

        saveButton.setOnClickListener {
            saveTimetableEntry()
        }
    }
    private fun showTimePicker(editText: TextInputEditText) {
        val calendar = Calendar.getInstance()

        val timePicker = TimePickerDialog(
            this,
            { _, hour, minute ->
                val formattedTime = String.format(
                    "%02d:%02d",
                    hour,
                    minute
                )
                editText.setText(formattedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        timePicker.show()
    }

    private fun saveTimetableEntry() {
        val day = dayAutoComplete.text.toString().trim()
        val subject = subjectEditText.text.toString().trim()
        val startTime = startTimeEditText.text.toString().trim()
        val endTime = endTimeEditText.text.toString().trim()
        val venue = venueEditText.text.toString().trim()

        if (
            day.isEmpty() ||
            subject.isEmpty() ||
            startTime.isEmpty() ||
            endTime.isEmpty() ||
            venue.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Please fill all fields",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val values = ContentValues().apply {
            put("day", day)
            put("subject", subject)
            put("startTime", startTime)
            put("endTime", endTime)
            put("venue", venue)
        }

        val db = dbHelper.writableDatabase
        val result = db.insert("timetable", null, values)
        db.close()

        if (result != -1L) {
            Toast.makeText(
                this,
                "Timetable entry added successfully",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        } else {
            Toast.makeText(
                this,
                "Failed to add timetable entry",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}