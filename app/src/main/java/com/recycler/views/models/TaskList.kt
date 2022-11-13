package com.recycler.views.models

import android.os.Parcel
import android.os.Parcelable

/*Parcelable let you break down objects into types the intent system is familiar with */
class TaskList(
    val name: String,
    val tasks: ArrayList<String> = ArrayList()) : Parcelable {

    // Reading from a parcel,2nd constructor for TaskList object to be created from passed-in Parcel
        constructor(source: Parcel) : this(
            source.readString()!!,
            source.createStringArrayList()!!
        )

    override fun describeContents() = 0

    //Writing to a Parcel-called when Parcel need to be created from TaskList object.
    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(name)
        p0.writeStringList(tasks)
    }

    //Fulfilling static interface requirements
    companion object CREATOR: Parcelable.Creator<TaskList>{
        // Calling your constructor
        override fun createFromParcel(p0: Parcel): TaskList = TaskList(p0)
        override fun newArray(p0: Int): Array<TaskList?> = arrayOfNulls(p0)
    }

}