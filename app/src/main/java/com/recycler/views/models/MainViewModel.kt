package com.recycler.views.models

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

//Update constructor to store a SharedPreference property.hence allow write key-value pairs to SP.
class MainViewModel(private val sharedPreferences: SharedPreferences): ViewModel() {

    //Add lambda 'onListAdded' used to inform other interested classes when list is added
    lateinit var onListAdded: (() -> Unit)

    //Property 'lists' is lazily created-until you call property,the property is empty.
    //Once called,the property will be populated by calling retrieveLists().hence avoid querying 4 unnecessary data until needed.
    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    //getting all saved TaskList from SP.
    private fun retrieveLists() : MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        //Looping and recreating TaskList objects from the HashSets.
        for (taskList in sharedPreferencesContents){
            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashSet)
            taskLists.add(list)
        }
        return taskLists
    }

    //takes a TaskList parameter saved to SP as set of strings.
    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke() //invoke the lambda to let interested classes know about the new list.
    }
}