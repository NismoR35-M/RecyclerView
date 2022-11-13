package com.recycler.views.models

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//add constructor for factory to receive an instance of SP used to create MainViewModel.
class MainViewModelFactory(private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    //override the create method from interface which will return instance of MainViewModel that uses SP
    // field within its constructor.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(sharedPreferences) as T
    }
}