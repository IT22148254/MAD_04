package com.example.mad_lab_04

// TaskListAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskListAdapter(context: Context, tasks: List<Task>) :
    ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false)
        }

        val currentTask = getItem(position)
        val textViewTitle: TextView = itemView!!.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

        textViewTitle.text = currentTask?.title
        textViewDescription.text = currentTask?.description

        return itemView
    }
}
