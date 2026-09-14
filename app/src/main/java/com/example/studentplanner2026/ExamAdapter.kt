package com.example.studentplanner2026

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExamAdapter (
    private val exams: List<Exam>
): RecyclerView.Adapter<ExamAdapter.ExamViewHolder>(){

    inner class ExamViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

                val subjectTextView: TextView=itemView.findViewById<TextView>(R.id.tvExamSubject)
                val dateTextView: TextView=itemView.findViewById<TextView>(R.id.tvExamDate)
                val timeTextView: TextView=itemView.findViewById<TextView>(R.id.tvExamTime)
                val venueTextView: TextView=itemView.findViewById<TextView>(R.id.tvExamVenue)
            }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam_item, parent, false)
        return ExamViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val exam = exams[position]
        holder.subjectTextView.text = exam.subject
        holder.dateTextView.text = "Date: ${exam.examDate}"
        holder.timeTextView.text = "Time: ${exam.examTime}"
        holder.venueTextView.text = "Venue: ${exam.venue}"
    }

    override fun getItemCount(): Int {
        return exams.size
    }
}