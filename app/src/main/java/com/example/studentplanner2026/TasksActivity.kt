package com.example.studentplanner2026

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TasksActivity : AppCompatActivity() {
    private lateinit var recyclerTasks: RecyclerView
    private lateinit var btnAddTask: Button
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tasks)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerTasks = findViewById(R.id.recyclerTasks)
        btnAddTask = findViewById(R.id.btnAddTask)
        dbHelper = DatabaseHelper(this)

        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        recyclerTasks.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {

        val tasks = ArrayList<Task>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM tasks ORDER BY id DESC",
            null
        )

        while (cursor.moveToNext()) {

            val task = Task(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                dueDate = cursor.getString(cursor.getColumnIndexOrThrow("dueDate")),
                priority = cursor.getString(cursor.getColumnIndexOrThrow("priority")),
                isCompleted = cursor.getInt(
                    cursor.getColumnIndexOrThrow("isCompleted")
                ) == 1
            )

            tasks.add(task)
        }

        cursor.close()
        db.close()

        recyclerTasks.adapter = TaskAdapter(
            tasks,
            onDeleteClick = { task -> deleteTask(task) },
            onEditClick = { task -> editTask(task) },
            onCompletionChanged = { task, isCompleted ->
                updateTaskCompletion(task, isCompleted)
            }
        )
    }

    private fun updateTaskCompletion(task: Task, isCompleted: Boolean) {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("isCompleted", if (isCompleted) 1 else 0)
        }

        db.update(
            "tasks",
            values,
            "id = ?",
            arrayOf(task.id.toString())
        )

        db.close()

        loadTasks()
    }

    private fun deleteTask(task: Task) {

        val db = dbHelper.writableDatabase

        db.delete(
            "tasks",
            "id = ?",
            arrayOf(task.id.toString())
        )

        db.close()

        loadTasks()
    }

    private fun editTask(task: Task) {

        val intent = Intent(this, EditTaskActivity::class.java)

        intent.putExtra("taskId", task.id)
        intent.putExtra("taskTitle", task.title)
        intent.putExtra("taskDescription", task.description)
        intent.putExtra("taskDueDate", task.dueDate)
        intent.putExtra("taskPriority", task.priority)


        startActivity(intent)
    }

}