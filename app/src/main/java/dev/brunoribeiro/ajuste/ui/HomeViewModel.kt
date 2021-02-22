package dev.brunoribeiro.ajuste.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.repository.ServiceRepository
import kotlinx.coroutines.launch

class HomeViewModel(val repository: ServiceRepository): ViewModel() {

    val allTasks = repository.allTasks


    fun getTasks(){
        viewModelScope.launch {
            repository.getTasks()
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

}