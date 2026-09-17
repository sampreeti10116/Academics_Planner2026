package com.example.studentplanner2026

import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class EditTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var etTaskTitle: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDueDate: EditText
    private lateinit var etTaskPriority: EditText
    private lateinit var btnUpdateTask: Button
    private lateinit var btnBackEditTask: Button

    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_task)

        dbHelper = DatabaseHelper(this)

        btnBackEditTask = findViewById(R.id.btnBackEditTask)
        btnBackEditTask.setOnClickListener {
            finish()
        }

        etTaskTitle = findViewById(R.id.edtxtEditTaskTitle)
        etTaskDescription = findViewById(R.id.edtxtEditTaskDescription)
        etTaskDueDate = findViewById(R.id.etEditTaskDueDate)
        etTaskPriority = findViewById(R.id.etEditTaskPriority)
        btnUpdateTask = findViewById(R.id.btnUpdateTask)

        taskId = intent.getIntExtra("taskId", -1)

        etTaskTitle.setText(intent.getStringExtra("taskTitle") ?: "")
        etTaskDescription.setText(intent.getStringExtra("taskDescription") ?: "")
        etTaskDueDate.setText(intent.getStringExtra("taskDueDate") ?: "")
        etTaskPriority.setText(intent.getStringExtra("taskPriority") ?: "")

        // Open Date Picker
        etTaskDueDate.setOnClickListener {
            showDatePicker()
        }

        btnUpdateTask.setOnClickListener {
            updateTask()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val currentDate = etTaskDueDate.text.toString().trim()

        if (currentDate.isNotEmpty()) {
            try {
                val parts = currentDate.split("-")

                if (parts.size == 3) {
                    calendar.set(
                        parts[0].toInt(),
                        parts[1].toInt() - 1,
                        parts[2].toInt()
                    )
                }
            } catch (e: Exception) {
                // Keep today's date if the stored date cannot be parsed
            }
        }

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

        datePickerDialog.datePicker.minDate =
            System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }

    private fun updateTask() {

        val title = etTaskTitle.text.toString().trim()
        val description = etTaskDescription.text.toString().trim()
        val dueDate = etTaskDueDate.text.toString().trim()
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

        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("dueDate", dueDate)
            put("priority", priority)
        }

        val db = dbHelper.writableDatabase

        val result = db.update(
            "tasks",
            values,
            "id = ?",
            arrayOf(taskId.toString())
        )

        db.close()

        if (result > 0) {
            Toast.makeText(
                this,
                "Task updated successfully",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        } else {
            Toast.makeText(
                this,
                "Failed to update task",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}