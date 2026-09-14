package com.example.studentplanner2026

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ExamActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerExams: RecyclerView
    private lateinit var btnAddExam: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exam)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DatabaseHelper(this)
        recyclerExams = findViewById(R.id.recyclerExams)
        recyclerExams.layoutManager= LinearLayoutManager(this)
        btnAddExam = findViewById(R.id.btnAddExam)
        btnAddExam.setOnClickListener {
            val intent = Intent(this, AddExamActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        loadExams()
    }
    private fun loadExams(){
        val exams= ArrayList<Exam>()
        val db=dbHelper.readableDatabase
        val cursor: Cursor=db.rawQuery(
            "SELECT * FROM exams ORDER BY id DESC",
            null
        )
        while (cursor.moveToNext()){
            val exam = Exam(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                subject = cursor.getString(
                    cursor.getColumnIndexOrThrow("subject")
                ),
                examDate = cursor.getString(
                    cursor.getColumnIndexOrThrow("examDate")
                ),
                examTime = cursor.getString(
                    cursor.getColumnIndexOrThrow("examTime")
                ),
                venue = cursor.getString(
                    cursor.getColumnIndexOrThrow("venue")
                )
            )

            exams.add(exam)
        }
        cursor.close()
        db.close()
        recyclerExams.adapter= ExamAdapter(exams)
    }
}
