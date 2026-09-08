package com.example.studentplanner2026

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class SubjectsActivity : AppCompatActivity() {
    lateinit var btnAddSub : MaterialButton
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
            startActivity(intent)
        }
    }
}