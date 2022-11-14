package com.recycler.views.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.recycler.views.R
import com.recycler.views.databinding.ActivityListDetailBinding
import com.recycler.views.ui.fragments.ListDetailFragment
import com.recycler.views.ui.models.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailBinding
    lateinit var viewModel: ListDetailViewModel
    lateinit var fragment: ListDetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //assign the title of activity to name of the list
        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }
        //setting up viewModel
        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        //FAB
        binding.addTaskButton.setOnClickListener { showCreateTaskDialog() }
    }

    //Dialog asking user for task to add to the list
    private fun showCreateTaskDialog() {
        //EditText to receive text input from user
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        //Create AlertDialog builder then use method chaining
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) {
                dialog, _->
                //in +ve button click-Listener you can access EditText to grap text input
                val task = taskEditText.text.toString()
                //notify the ViewModel a new item was added
                viewModel.addTask(task)
                //once ViewModel is aware,close the dialog by dismissing it.
                dialog.dismiss()
            }
                //create and show alertDialog without needing extra AlertDialogBuilder as separate variabl
            .create()
            .show()
    }
}