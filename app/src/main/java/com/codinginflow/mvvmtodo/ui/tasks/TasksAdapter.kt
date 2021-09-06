package com.codinginflow.mvvmtodo.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtodo.databinding.ItemTaskBinding
import com.codinginflow.mvvmtodo.modal.Task

class TasksAdapter  : ListAdapter<Task, TasksAdapter.TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding  = ItemTaskBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class TaskViewHolder(private val binding : ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(task: Task){
            binding.apply {
                checkboxCompleted.isChecked = task.completed
                txtViewTaskName.text = task.title
                txtViewTaskName.paint.isStrikeThruText = task.completed
                imgViewTaskPriority.isVisible = task.important

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id==newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}