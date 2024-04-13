//package com.example.todolist
//
//import android.os.Bundle
//import android.widget.EditText
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var taskAdapter: TaskAdapter
//    private lateinit var taskList: MutableList<Task>
//    private lateinit var fabAddTask: FloatingActionButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        recyclerView = findViewById(R.id.recycler_view)
//        fabAddTask = findViewById(R.id.fab_add_task)
//
//        taskList = mutableListOf()
//        taskAdapter = TaskAdapter(taskList)
//        recyclerView.adapter = taskAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Set onClickListener for the plus button
//        fabAddTask.setOnClickListener {
//            showAddTaskDialog()
//        }
//
//
//
//    }
//
//    private fun showAddTaskDialog() {
//        val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
//
//        AlertDialog.Builder(this)
//            .setTitle("Add New Task")
//            .setView(dialogView)
//            .setPositiveButton("Add") { dialog, _ ->
//                val taskName =
//                    dialogView.findViewById<EditText>(R.id.edit_text_task_name).text.toString()
//                val newTask = Task(taskName, false)
//                taskList.add(newTask)
//                taskAdapter.notifyDataSetChanged()
//                dialog.dismiss()
//            }
//            .setNegativeButton("Cancel") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .create()
//            .show()
//    }
//}
package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: MutableList<Task>
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var emptyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        fabAddTask = findViewById(R.id.fab_add_task)
        emptyTextView = findViewById(R.id.text_empty)

        taskList = mutableListOf()
        taskAdapter = TaskAdapter(taskList)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set onClickListener for the plus button
        fabAddTask.setOnClickListener {
            taskAdapter.showAddTaskDialog(this)
        }

        // Show or hide empty text based on task list size
        updateEmptyTextVisibility()
    }



    fun updateEmptyTextVisibility() {
        emptyTextView.isVisible = taskList.isEmpty()
    }
}
