package com.example.studentplanner2026

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SubjectAdapter (private val subjects: List<Subject>, private val onDeleteClick: (Int) -> Unit):
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>(){
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val subjectName: TextView = itemView.findViewById<TextView>(R.id.tvSubjectName)
        val subjectCode: TextView = itemView.findViewById(R.id.tvSubjectCode)
        val deleteButton: MaterialButton = itemView.findViewById(R.id.btnDeleteSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)

        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {

        val subject = subjects[position]

        holder.subjectName.text = subject.name
        holder.subjectCode.text = subject.code
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}