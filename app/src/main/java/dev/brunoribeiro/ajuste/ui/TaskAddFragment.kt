package dev.brunoribeiro.ajuste.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.brunoribeiro.ajuste.R
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
        var type = 0

        binding.typeUrgente.setOnClickListener {
            type = Task.URGENTE
            binding.typeName.text = " Urgente"
            binding.typeName.setTextColor(binding.root.resources.getColor(R.color.red))
        }
        binding.typeIntermediario.setOnClickListener {
            type = Task.INTERMEDIARIO
            binding.typeName.text = " Intermediario"
            binding.typeName.setTextColor(binding.root.resources.getColor(R.color.yellow))
        }
        binding.typeNormal.setOnClickListener {
            type = Task.NORMAL
            binding.typeName.text = " Normal"
            binding.typeName.setTextColor(binding.root.resources.getColor(R.color.blue))
        }

        binding.toolbarAddTask.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_btn_adicionar_task -> {
                    if (binding.etTaskAdd.text.isNullOrEmpty() && type == 0) {
                        Toast.makeText(context, "Campos vazios", Toast.LENGTH_SHORT).show()
                        false
                    } else {
                        sendTask(getTask(binding.etTaskAdd.text.toString(), type))
                        true
                    }
                }
                else -> false
            }
        }

        binding.etTaskAdd.setScroller(Scroller(context))
        binding.etTaskAdd.maxLines = 3
        binding.etTaskAdd.isVerticalScrollBarEnabled = true
        binding.etTaskAdd.movementMethod = ScrollingMovementMethod()


        return binding.root
    }

    fun getTask(title: String, type: Int): Task{

        return Task(null, title, type, false)
    }

    fun sendTask(task: Task){
        viewModel.sendTask(task)
        activity?.onBackPressed()
    }

}