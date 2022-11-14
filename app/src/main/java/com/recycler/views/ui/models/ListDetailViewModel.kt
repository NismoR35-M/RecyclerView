package com.recycler.views.ui.models

import androidx.lifecycle.ViewModel
import com.recycler.views.logic.TaskList

class ListDetailViewModel() : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)
    lateinit var list: TaskList

    //method to add task to the list
    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}