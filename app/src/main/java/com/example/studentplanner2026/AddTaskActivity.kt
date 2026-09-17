package com.example.studentplanner2026

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import java.util.Locale

class AddTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var etTaskTitle: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDueDate: EditText
    private lateinit var etTaskPriority: EditText
    private lateinit var btnSaveTask: Button
    private lateinit var etDueTime: TextInputEditText
    private lateinit var btnBackAddTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_task)

        dbHelper = DatabaseHelper(this)

        btnBackAddTask = findViewById(R.id.btnBackAddTask)
        btnBackAddTask.setOnClickListener {
            finish()
        }

        etTaskTitle = findViewById(R.id.edtxtTaskTitle)
        etTaskDescription = findViewById(R.id.edtxtTaskDescription)
        etTaskDueDate = findViewById(R.id.etTaskDueDate)
        etTaskPriority = findViewById(R.id.etTaskPriority)
        btnSaveTask = findViewById(R.id.btnSaveTask)
        etDueTime = findViewById(R.id.etTaskDueTime)

        // Open Date Picker
        etTaskDueDate.setOnClickListener {
            showDatePicker()
        }

        // Open Time Picker
        etDueTime.setOnClickListener {
            showTimePicker()
        }

        btnSaveTask.setOnClickListener {
            saveTask()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->

                val formattedDate = String.format(
                    Locale.getDefault(),
                    "%04d-%02d-%02d",
                    selectedYear,
                    selectedMonth + 1,
                    selectedDay
                )

                etTaskDueDate.setText(formattedDate)
            },
            year,
            month,
            day
        )

        // Prevent selecting past dates
        datePickerDialog.datePicker.minDate =
            System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->

                val formattedTime = String.format(
                    Locale.getDefault(),
                    "%02d:%02d",
                    selectedHour,
                    selectedMinute
                )

                etDueTime.setText(formattedTime)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }

    private fun saveTask() {
        val title = etTaskTitle.text.toString().trim()
        val description = etTaskDescription.text.toString().trim()
        val dueDate = etTaskDueDate.text.toString().trim()
        val dueTime = etDueTime.text.toString().trim()
        val priority = etTaskPriority.text.toString().trim()

        if (title.isEmpty()) {
            etTaskTitle.error = "Enter task title"
            return
        }

        if (dueDate.isEmpty()) {
            etTaskDueDate.error = "Select due date"
            return
        }

        if (priority.isEmpty()) {
            etTaskPriority.error = "Enter priority"
            return
        }

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("dueDate", dueDate)
            put("dueTime", dueTime)
            put("priority", priority)
            put("isCompleted", 0)
        }

        val result = db.insert("tasks", null, values)

        if (result != -1L) {

            val taskId = result.toInt()

            TaskNotificationScheduler.scheduleTaskNotification(
                context = this,
                taskId = taskId,
                title = title,
                description = description,
                dueDate = dueDate,
                dueTime = dueTime
            )

            Toast.makeText(
                this,
                "Task saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            finish()

        } else {

            Toast.makeText(
                this,
                "Failed to save task",
                Toast.LENGTH_SHORT
            ).show()
        }

        db.close()
    }
}