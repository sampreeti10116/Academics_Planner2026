package com.example.studentplanner2026

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SubjectsActivity : AppCompatActivity() {
    private lateinit var recyclerSubjects: RecyclerView
    private val subjects = mutableListOf<Subject>()
    private lateinit var dbHelper: DatabaseHelper
    lateinit var btnAddSub : MaterialButton
    private val addSubjectLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode==RESULT_OK){
                val name = result.data?.getStringExtra("subjectName")
                val code = result.data?.getStringExtra("subjectCode")

                if (name != null && code != null) {
                    subjects.add(Subject(name, code))
                    recyclerSubjects.adapter?.notifyItemInserted(subjects.size - 1)

                }
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

        recyclerSubjects = findViewById(R.id.RecyclerSubjects)

        recyclerSubjects.layoutManager = LinearLayoutManager(this)
        dbHelper = DatabaseHelper(this)

        recyclerSubjects.adapter = SubjectAdapter(
            subjects,

            { position ->

                val subject = subjects[position]

                val db = dbHelper.writableDatabase

                db.delete(
                    "subjects",
                    "name = ? AND code = ?",
                    arrayOf(subject.name, subject.code)
                )

                db.close()

                subjects.removeAt(position)

                recyclerSubjects.adapter?.notifyItemRemoved(position)
            },

            { position ->

                val subject = subjects[position]

                val intent = Intent(this, EditSubjectActivity::class.java)

                intent.putExtra("subjectName", subject.name)
                intent.putExtra("subjectCode", subject.code)

                startActivity(intent)
            }
        )
        btnAddSub=findViewById<MaterialButton>(R.id.btnAddSubject)
        btnAddSub.setOnClickListener {
            val intent= Intent(this, AddSubjectActivity::class.java)
            addSubjectLauncher.launch(intent)
        }
        loadSubjects()
    }

    private fun loadSubjects() {

        subjects.clear()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT name, code FROM subjects", null)

        while (cursor.moveToNext()) {

            val name = cursor.getString(0)
            val code = cursor.getString(1)

            subjects.add(Subject(name, code))
        }

        cursor.close()
        db.close()

        recyclerSubjects.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        loadSubjects()
    }
}