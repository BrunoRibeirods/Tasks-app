package dev.brunoribeiro.ajuste.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.brunoribeiro.ajuste.databinding.FragmentHomeBinding
import dev.brunoribeiro.ajuste.databinding.FragmentTaskAddBinding
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.repository.ServiceRepository

class TaskAddFragment : Fragment() {

    private var _binding: FragmentTaskAddBinding? = null
    private val binding get() = _binding!!
    lateinit var repository: ServiceRepository

    val viewModel by viewModels<TaskAddViewModel>(){
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TaskAddViewModel(repository) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskAddBinding.inflate(inflater, container, false)
        repository = ServiceRepository.getInstance(requireContext())
        binding.toolbarAddTask.setNavigationOnClickListener { activity?.onBackPressed() }

        binding.btnAddTask.setOnClickListener {
            if (binding.etTaskAdd.text.isNullOrEmpty()){
                Toast.makeText(context, "Campos vazios", Toast.LENGTH_SHORT).show()
            }else{
                sendTask(getTask(binding.etTaskAdd.text.toString()))
            }

        }
        return binding.root
    }

    fun getTask(title: String): Task{

        return Task(null, title)
    }

    fun sendTask(task: Task){
        viewModel.sendTask(task)
        activity?.onBackPressed()
    }

}