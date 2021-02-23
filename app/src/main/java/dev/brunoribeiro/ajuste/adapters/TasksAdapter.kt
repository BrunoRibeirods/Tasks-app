package dev.brunoribeiro.ajuste.adapters

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import dev.brunoribeiro.ajuste.R
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.databinding.ItemTaskBinding
import dev.brunoribeiro.ajuste.repository.ServiceRepository
import dev.brunoribeiro.ajuste.ui.HomeViewModel

class TasksAdapter(private val listOfTasks: MutableList<Task>, val deleteDao: HomeViewModel): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent, deleteDao)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(listOfTasks[position])

    override fun getItemCount(): Int = listOfTasks.size

    fun removeAt(position: Int) {
        deleteDao.deleteTask(listOfTasks[position])
        listOfTasks.removeAt(position)
        notifyItemRemoved(position)
    }




    class TaskViewHolder(val binding: ItemTaskBinding, val viewModel: HomeViewModel): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task){
            with(binding){
                titleTask.text = item.title
                taskType.backgroundTintList = setTypeColor(item.type)
                checkTask.isChecked = item.checked!!


                checkTask.setOnClickListener {
                    viewModel.updateTask(!item.checked!!, item)
                }
            }
        }


        companion object{
            fun from(parent: ViewGroup, viewModel: HomeViewModel): TaskViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemTaskBinding.inflate(inflater, parent, false)
                return TaskViewHolder(binding, viewModel)
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