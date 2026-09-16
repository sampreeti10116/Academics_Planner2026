package com.example.studentplanner2026

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var etTaskTitle: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDueDate: EditText
    private lateinit var etTaskPriority: EditText
    private lateinit var btnSaveTask: Button
    private lateinit var btnBackAddTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_task)

        dbHelper = DatabaseHelper(this)

        btnBackAddTask = findViewById(R.id.btnBackAddTask)
        btnBackAddTask.setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }


        etTaskTitle = findViewById(R.id.edtxtTaskTitle)
        etTaskDescription = findViewById(R.id.edtxtTaskDescription)
        etTaskDueDate = findViewById(R.id.etTaskDueDate)
        etTaskPriority = findViewById(R.id.etTaskPriority)
        btnSaveTask = findViewById(R.id.btnSaveTask)

        btnSaveTask.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        val title = etTaskTitle.text.toString().trim()
        val description = etTaskDescription.text.toString().trim()
        val dueDate = etTaskDueDate.text.toString().trim()
        val priority = etTaskPriority.text.toString().trim()

        if (title.isEmpty()) {
            etTaskTitle.error = "Enter task title"
            return
        }

        if (dueDate.isEmpty()) {
            etTaskDueDate.error = "Enter due date"
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
            put("priority", priority)
            put("isCompleted", 0)
        }

        val result = db.insert("tasks", null, values)

        if (result != -1L) {
            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()

            finish()
        } else {
            Toast.makeText(this, "Fa" +
                    "iled to save task", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }
}