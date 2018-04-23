package org.hedgehog.tasksplanningapp.adapters

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_task.view.*

import org.hedgehog.tasksplanningapp.models.Task
import org.hedgehog.tasksplanningapp.R


class TasksRecyclerViewAdapter(private val activity: Activity, private val mValues: List<Task>)
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
        holder.subtasks.adapter = SubtasksRecyclerViewAdapter(item.subtasks!!.toMutableList())
        holder.subtasks.layoutManager = LinearLayoutManager(activity)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val name: TextView = mView.task_name
        val desc: TextView = mView.task_description
        val deadline: TextView = mView.task_deadline
        val subtasks: RecyclerView = mView.task_subtasks
    }
}
