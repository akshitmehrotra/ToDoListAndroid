package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskCheckbox: CheckBox = itemView.findViewById(R.id.task_checkbox)
        private val taskText: TextView = itemView.findViewById(R.id.task_text)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

        fun bind(task: Task) {
            taskCheckbox.isChecked = task.isChecked
            taskText.text = task.description

            deleteButton.setOnClickListener {
                taskList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                (itemView.context as? MainActivity)?.updateEmptyTextVisibility()
            }
        }
    }

    fun showAddTaskDialog(activity: AppCompatActivity) {
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_add_task, null)

        AlertDialog.Builder(activity)
            .setTitle("Add New Task")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val taskName = dialogView.findViewById<EditText>(R.id.edit_text_task_name).text.toString()
                val newTask = Task(taskName, false)
                taskList.add(newTask)
                notifyDataSetChanged()

                // Update empty text visibility after adding task
                (activity as? MainActivity)?.updateEmptyTextVisibility()

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
