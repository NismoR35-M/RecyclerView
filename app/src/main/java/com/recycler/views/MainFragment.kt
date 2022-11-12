package com.recycler.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.recycler.views.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)

        //Letting recycler view arrange it items in linear layout
        binding.listsRecyclerview.layoutManager=LinearLayoutManager(requireContext())

        //Adapter for the recyclerview is set
        binding.listsRecyclerview.adapter=ListSelectionRecyclerViewAdapter()
        return binding.root
    }

}