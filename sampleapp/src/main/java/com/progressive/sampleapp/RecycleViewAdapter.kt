package com.progressive.sampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.progressive.kherkin.sampleapp.R

class RecycleViewAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button

        init {
            button = view.findViewById(R.id.buttonItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.button_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.button.text = dataSet[position]
        viewHolder.button.setOnClickListener {
            viewHolder.button.text = viewHolder.itemView.context.getString(R.string.button_clicked)
        }
    }

    override fun getItemCount() = dataSet.size
}