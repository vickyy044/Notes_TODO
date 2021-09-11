package com.notesTODOApplication.mvvmtodo.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.notesTODOApplication.mvvmtodo.databinding.ItemTaskBinding
import com.notesTODOApplication.mvvmtodo.modal.Task

class TasksAdapter (private  val listener : OnItemClickListener) : ListAdapter<Task, TasksAdapter.TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding  = ItemTaskBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TaskViewHolder(private val binding : ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position!=RecyclerView.NO_POSITION){
                        val task=  getItem(position)
                        listener.onItemClick(task)
                    }
                }
                checkboxCompleted.setOnClickListener {
                    val position = adapterPosition
                    if(position!= RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onCheckBoxClick(task, checkboxCompleted.isChecked)
                    }
                }
            }
        }

        fun bind(task: Task){
            binding.apply {
                checkboxCompleted.isChecked = task.completed
                txtViewTaskName.text = task.title
                txtViewTaskName.paint.isStrikeThruText = task.completed
                imgViewTaskPriority.isVisible = task.important

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task:Task)
        fun onCheckBoxClick(task: Task, isChecked : Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id==newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}