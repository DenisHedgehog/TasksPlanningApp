package org.hedgehog.tasksplanningapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import org.hedgehog.tasksplanningapp.R
import org.hedgehog.tasksplanningapp.models.Subtask

class SubtasksRecyclerViewAdapter(private val mValues: List<Subtask>)
    : RecyclerView.Adapter<SubtasksRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.status.isChecked = item.status != "Active"
        holder.name.text = item.name
    }

    override fun getItemCount(): Int = 0

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var status: RadioButton
        lateinit var name: TextView
    }

}