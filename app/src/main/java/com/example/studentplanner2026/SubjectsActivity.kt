package com.example.studentplanner2026

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SubjectsActivity : AppCompatActivity() {
    private lateinit var recyclerSubjects: RecyclerView
    private val subjects = mutableListOf<Subject>()
    lateinit var btnAddSub : MaterialButton
    private val addSubjectLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            val name = result.data?.getStringExtra("subjectName")
            val code = result.data?.getStringExtra("subjectCode")

            if (name != null && code != null) {
                subjects.add(Subject(name, code))
                recyclerSubjects.adapter?.notifyItemInserted(subjects.size - 1)

            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subjects)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAddSub=findViewById<MaterialButton>(R.id.btnAddSubject)
        btnAddSub.setOnClickListener {
            val intent= Intent(this, AddSubjectActivity::class.java)
            addSubjectLauncher.launch(intent)
        }
    }
}