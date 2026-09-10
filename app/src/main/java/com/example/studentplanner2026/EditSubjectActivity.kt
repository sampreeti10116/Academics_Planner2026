package com.example.studentplanner2026

import android.content.ContentValues
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class EditSubjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_subject)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val subjectName = findViewById<EditText>(R.id.etEditSubjectName)
        val subjectCode = findViewById<EditText>(R.id.etEditSubjectCode)
        val updateButton = findViewById<MaterialButton>(R.id.btnUpdateSubject)

        val oldName = intent.getStringExtra("subjectName")
        val oldCode = intent.getStringExtra("subjectCode")

        subjectName.setText(oldName)
        subjectCode.setText(oldCode)

        updateButton.setOnClickListener {

            val newName = subjectName.text.toString()
            val newCode = subjectCode.text.toString()

            val dbHelper = DatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("name", newName)
            values.put("code", newCode)

            db.update(
                "subjects",
                values,
                "name = ? AND code = ?",
                arrayOf(oldName, oldCode)
            )

            db.close()

            finish()
        }
    }

}