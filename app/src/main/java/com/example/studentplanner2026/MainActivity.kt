package com.example.studentplanner2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var subjectsButton: Button
    lateinit var btnTasks: Button
    lateinit var btnExams: Button
    lateinit var btnTimetable: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        subjectsButton=findViewById<Button>(R.id.btnSubjects)
        subjectsButton.setOnClickListener {
            val intent= Intent(this, SubjectsActivity::class.java)
            startActivity(intent)
        }

        btnTasks = findViewById<Button>(R.id.btnTasks)
        btnTasks.setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }

        btnExams=findViewById<Button>(R.id.btnExams)
        btnExams.setOnClickListener {
            val intent= Intent(this, ExamActivity::class.java)
            startActivity(intent)
        }
        btnTimetable=findViewById<Button>(R.id.btnTimetable)
        btnTimetable.setOnClickListener {
            val intent= Intent(this, TimetableActivity::class.java)
            startActivity(intent)
        }
    }

}