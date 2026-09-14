package com.example.studentplanner2026

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class AddExamActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var etExamSubject: EditText
    private lateinit var etExamDate: EditText
    private lateinit var etExamTime: EditText
    private lateinit var etExamVenue: EditText
    private lateinit var btnSaveExam: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_exam)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DatabaseHelper(this)

        etExamSubject = findViewById(R.id.etExamSubject)
        etExamDate = findViewById(R.id.etExamDate)
        etExamTime = findViewById(R.id.etExamTime)
        etExamVenue = findViewById(R.id.etExamVenue)
        btnSaveExam = findViewById(R.id.btnSaveExam)

        etExamDate.setOnClickListener {
            showDatePicker()
        }
        etExamTime.setOnClickListener {
            showTimePicker()
        }

        btnSaveExam.setOnClickListener {
            saveExam()
        }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                etExamDate.setText("$dayOfMonth/${month + 1}/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                etExamTime.setText(
                    String.format("%02d:%02d", hourOfDay, minute)
                )
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        timePickerDialog.show()
    }

    private fun saveExam() {
        val subject = etExamSubject.text.toString().trim()
        val examDate = etExamDate.text.toString().trim()
        val examTime = etExamTime.text.toString().trim()
        val venue = etExamVenue.text.toString().trim()

        if (subject.isEmpty()) {
            etExamSubject.error = "Enter subject name"
            return
        }

        if (examDate.isEmpty()) {
            etExamDate.error = "Select exam date"
            return
        }

        if (examTime.isEmpty()) {
            etExamTime.error = "Select exam time"
            return
        }

        if (venue.isEmpty()) {
            etExamVenue.error = "Enter exam venue"
            return
        }

        val values = ContentValues().apply {
            put("subject", subject)
            put("examDate", examDate)
            put("examTime", examTime)
            put("venue", venue)
        }

        val db = dbHelper.writableDatabase
        val result = db.insert("exams", null, values)
        db.close()

        if (result != -1L) {
            Toast.makeText(
                this,
                "Exam saved with ID: $result",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}