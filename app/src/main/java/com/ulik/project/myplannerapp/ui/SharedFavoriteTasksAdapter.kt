package com.ulik.project.myplannerapp.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.FtsOptions
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.formatDate
import kotlinx.android.synthetic.main.item_task.view.*

class SharedFavoriteTasksAdapter(val tasksViewModel: TasksViewModel) :
    RecyclerView.Adapter<SharedFavoriteTasksAdapter.SharedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return SharedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SharedViewHolder, position: Int) {
        holder.bind(tasksViewModel.favoriteTasks[position])
    }

    override fun getItemCount(): Int {
        return tasksViewModel.favoriteTasks.size
    }

    fun update(list: List<Task>) {
        tasksViewModel.favoriteTasks.clear()
        tasksViewModel.favoriteTasks.addAll(list)
        notifyDataSetChanged()
    }

    inner class SharedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            with(itemView) {

                if (task.isCompleted) {
                    title_tv.paintFlags = title_tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    title_tv.paintFlags = View.INVISIBLE
                }

                title_tv.text = task.title
                description.text = task.description
                complete_checkbox.isChecked = task.isCompleted


                if (task.isFavorite) {
                    iv_isfavorite.setImageResource(R.drawable.ic_baseline_favorite_red)

                } else {
                    iv_isfavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                }

                if (task.isShared){
                    iv_isShared.setImageResource(R.drawable.ic_baseline_share_red)
                }else{
                    iv_isShared.setImageResource(R.drawable.ic_baseline_share_24)
                }
                itemView.complete_checkbox.setOnClickListener {
                    tasksViewModel.taskUpdate(task.copy(isCompleted = complete_checkbox.isChecked))

                }
                iv_isfavorite.setOnClickListener {
                    tasksViewModel.taskUpdate(task.copy(isFavorite = !task.isFavorite))
                    tasksViewModel.getFavoriteTask()
                    notifyDataSetChanged()
                }

                iv_isShared.setOnClickListener {
                    tasksViewModel.shareTask(task.copy(isShared = !task.isShared))
                }


                itemView.setOnClickListener {
                    tasksViewModel.showTaskDetails(task)

                }

                tv_date.text = formatDate(task)
            }
        }
    }
}