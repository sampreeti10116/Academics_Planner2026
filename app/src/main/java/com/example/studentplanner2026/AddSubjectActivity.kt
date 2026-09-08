package com.example.studentplanner2026

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class AddSubjectActivity : AppCompatActivity() {
    lateinit var subjectName: EditText
    lateinit var subjectCode: EditText
    lateinit var btnSaveSubject: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_subject)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        subjectName=findViewById<EditText>(R.id.edtxtSubjectName)
        subjectCode=findViewById<EditText>(R.id.edtxtSubjectCode)
        btnSaveSubject=findViewById<MaterialButton>(R.id.btnSaveSubject)

        btnSaveSubject.setOnClickListener{
            val name = subjectName.text.toString()
            val code = subjectCode.text.toString()
            val intent = Intent()
            intent.putExtra("subjectName", name)
            intent.putExtra("subjectCode", code)

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}