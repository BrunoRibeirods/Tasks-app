package dev.brunoribeiro.ajuste.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.brunoribeiro.ajuste.R
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.databinding.ItemTaskBinding

class TasksAdapter(private val listOfTasks: List<Task>): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(listOfTasks[position])

    override fun getItemCount(): Int = listOfTasks.size

    class TaskViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task){
            with(binding){
                titleTask.text = item.title
                taskType.backgroundTintList = setTypeColor(item.type)
            }
        }


        companion object{
            fun from(parent: ViewGroup): TaskViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemTaskBinding.inflate(inflater, parent, false)
                return TaskViewHolder(binding)
            }
        }

        private fun setTypeColor(type: Int?): ColorStateList{
            val result = when(type){
                Task.URGENTE -> R.color.red
                Task.INTERMEDIARIO -> R.color.yellow
                Task.NORMAL -> R.color.blue
                else -> R.color.black
            }

            return ColorStateList.valueOf(binding.root.resources.getColor(result))
        }
    }




}