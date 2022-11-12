package com.recycler.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recycler.views.databinding.ListSelectionViewHolderBinding

class ListSelectionRecyclerViewAdapter : RecyclerView.Adapter<ListSelectionViewHolder>() {

    private val listTitles = arrayOf("MercedesGLC63","Alfa Romeo", "BMW")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        //Creates layoutInflater object from parent context and uses binding class to inflate itself
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        //ListSelectionViewHolder object is created,passing in binding and returning ViewHolder.
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text=(position + 1).toString()
        holder.binding.itemString.text=listTitles[position]
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }


}
