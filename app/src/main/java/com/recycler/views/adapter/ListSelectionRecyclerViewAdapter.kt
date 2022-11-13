package com.recycler.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recycler.views.databinding.ListSelectionViewHolderBinding
import com.recycler.views.models.TaskList

class ListSelectionRecyclerViewAdapter(private val lists : MutableList<TaskList>) : RecyclerView.Adapter<ListSelectionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        //Creates layoutInflater object from parent context and uses binding class to inflate itself
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        //ListSelectionViewHolder object is created,passing in binding and returning ViewHolder.
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text=(position + 1).toString()
        holder.binding.itemString.text=lists[position].name
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    //let adapter know of new list to display
    fun listsUpdated() {
        notifyItemInserted(lists.size-1)
    }

}
