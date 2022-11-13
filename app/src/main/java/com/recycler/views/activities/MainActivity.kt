package com.recycler.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.recycler.views.fragments.MainFragment
import com.recycler.views.R
import com.recycler.views.databinding.ActivityMainBinding
import com.recycler.views.models.MainViewModel
import com.recycler.views.models.MainViewModelFactory
import com.recycler.views.models.TaskList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    //The constant is used by the intent to refer to a list whenever it needs to pass one to new Activity.
    companion object{
        const val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewModel
        viewModel = ViewModelProvider(this,
        MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        )
            .get(MainViewModel::class.java)

        //connecting UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Fragment
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, MainFragment())
                .commitNow()
        }

        //FAB
        binding.fabButton.setOnClickListener{
            showCreateDialog()
        }

    }
    //Creating an intent
    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        //providing more info. to the receiver about action to be done.
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        //inform current activity to start another Activity
        startActivity(listDetailIntent)
    }

    //Creating AlertDialog
    private fun showCreateDialog(){
        //Retrieving strings
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        //Create an AlertDialog.Builder to help construct Dialog
        val builder=AlertDialog.Builder(this)
        //Edit text View is created to serve as input field
        val listTitleEditText= EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT //Telling android to show text-based keyboard

        //Title of dialog is set by calling setTitle() and also content of view
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        //adding a positive button to a Dialog.Tells a dialog a positive action has occurred and smth should happen
        builder.setPositiveButton(positiveButtonTitle){dialog, _ ->
            dialog.dismiss()
            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)
        }

        //Instruct the Dialog Builder to create the Dialog and display it on screen.
        builder.create().show()
    }
}