package com.example.todolist

data class Task(var description: String, var completed: Boolean = false) {
    var isChecked: Boolean = false
}
