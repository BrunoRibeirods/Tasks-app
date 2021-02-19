package dev.brunoribeiro.ajuste.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.repository.ServiceRepository
import kotlinx.coroutines.launch

class TaskAddViewModel(val repository: ServiceRepository): ViewModel() {


    fun sendTask(task: Task){
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
}