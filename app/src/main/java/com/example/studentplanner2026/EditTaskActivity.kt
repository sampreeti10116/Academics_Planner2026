package com.example.studentplanner2026

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditTaskActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var etTaskTitle: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDueDate: EditText
    private lateinit var etTaskPriority: EditText
    private lateinit var btnUpdateTask: Button
    lateinit var btnBackEditTask : Button

    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_task)
        btnBackEditTask=findViewById<Button>(R.id.btnBackEditTask)
        btnBackEditTask.setOnClickListener {
            val intent= Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }

        dbHelper = DatabaseHelper(this)

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

        btnUpdateTask.setOnClickListener {
            updateTask()
        }
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
            etTaskDueDate.error = "Enter due date"
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