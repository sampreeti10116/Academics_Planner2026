package com.example.studentplanner2026

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimetableAdapter(
    private val timetableEntries: List<Timetable>,
    private val onDeleteClick: (Timetable) -> Unit
) : RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    inner class TimetableViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val dayTextView: TextView =
            itemView.findViewById(R.id.tvTimetableDay)

        val subjectTextView: TextView =
            itemView.findViewById(R.id.tvTimetableSubject)

        val timeTextView: TextView =
            itemView.findViewById(R.id.tvTimetableTime)

        val venueTextView: TextView =
            itemView.findViewById(R.id.tvTimetableVenue)

        val deleteButton: View =
            itemView.findViewById(R.id.btnDeleteTimetable)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimetableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.timetable_item, parent, false)

        return TimetableViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TimetableViewHolder,
        position: Int
    ) {
        val entry = timetableEntries[position]

        holder.dayTextView.text = entry.day
        holder.subjectTextView.text = "Subject: ${entry.subject}"
        holder.timeTextView.text =
            "Time: ${entry.startTime} - ${entry.endTime}"
        holder.venueTextView.text = "Venue: ${entry.venue}"

        holder.deleteButton.setOnClickListener {
            onDeleteClick(entry)
        }
    }

    override fun getItemCount(): Int {
        return timetableEntries.size
    }
}