package com.example.studentplanner2026

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: List<Task>,
    private val onDeleteClick: (Task) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onCompletionChanged: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskTitle: TextView = itemView.findViewById(R.id.tvTaskTitle)
        val taskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val taskDueDate: TextView = itemView.findViewById(R.id.tvTaskDueDate)
        val taskPriority: TextView = itemView.findViewById(R.id.tvTaskPriority)
        val deleteButton: Button = itemView.findViewById(R.id.btnDeleteTask)
        val editButton: Button = itemView.findViewById(R.id.btnEditTask)
        val completionCheckBox: CheckBox = itemView.findViewById(R.id.checkTaskCompleted)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {

        val task = tasks[position]

        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description
        holder.taskDueDate.text = "Due Date: ${task.dueDate}"
        holder.taskPriority.text = "Priority: ${task.priority}"

        holder.deleteButton.setOnClickListener {
            onDeleteClick(task)
        }

        holder.editButton.setOnClickListener {
            onEditClick(task)
        }

        holder.completionCheckBox.setOnCheckedChangeListener(null)

        holder.completionCheckBox.isChecked = task.isCompleted

        holder.completionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onCompletionChanged(task, isChecked)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}