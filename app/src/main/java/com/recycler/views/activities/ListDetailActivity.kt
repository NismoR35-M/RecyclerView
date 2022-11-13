package com.recycler.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.recycler.views.R
import com.recycler.views.models.TaskList
import com.recycler.views.ui.main.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {
    //variable to store the list from mainActivity
    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        //reference the list in intent and assign to list variable
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        //assign the title of activity to name of the list
        title = list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}