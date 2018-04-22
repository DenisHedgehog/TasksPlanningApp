package org.hedgehog.tasksplanningapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView

import org.hedgehog.tasksplanningapp.models.Task
import org.hedgehog.tasksplanningapp.R
import org.hedgehog.tasksplanningapp.models.Subtask


class TasksRecyclerViewAdapter(private val mValues: List<Task>)
    : RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.name.text = item.name
        holder.desc.text = item.description
        holder.deadline.text = item.deadline.toString()
        val s = Subtask(0, "Kappa", "Active", 0)
        val s1 = Subtask(0, "Kappa", "Finished", 0)
        holder.subtasks.adapter = SubtasksRecyclerViewAdapter(arrayListOf(s, s, s1, s, s1))
    }

    override fun getItemCount(): Int = 0

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var name: TextView
        lateinit var desc: TextView
        lateinit var deadline: TextView
        lateinit var subtasks: RecyclerView
    }
}
