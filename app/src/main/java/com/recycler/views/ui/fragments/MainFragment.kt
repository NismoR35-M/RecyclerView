package com.recycler.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.recycler.views.ui.adapter.ListSelectionRecyclerViewAdapter
import com.recycler.views.databinding.FragmentMainBinding
import com.recycler.views.ui.models.MainViewModel
import com.recycler.views.ui.models.MainViewModelFactory
import com.recycler.views.logic.TaskList

class MainFragment(val clickListener:
MainFragmentInteractionListener) : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    interface MainFragmentInteractionListener{
        fun listItemTapped(list: TaskList)
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    companion object{
        fun newInstance(clickListener: MainFragmentInteractionListener)
        =MainFragment(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)

        //Letting recycler view arrange it items in linear layout
        binding.listsRecyclerview.layoutManager=LinearLayoutManager(requireContext())

        //viewModel
        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
        )
            .get(MainViewModel::class.java)
        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
        binding.listsRecyclerview.adapter = recyclerViewAdapter
        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }

        return binding.root
    }

    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }
}