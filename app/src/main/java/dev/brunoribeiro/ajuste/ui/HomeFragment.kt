package dev.brunoribeiro.ajuste.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.brunoribeiro.ajuste.R
import dev.brunoribeiro.ajuste.adapters.TasksAdapter
import dev.brunoribeiro.ajuste.databinding.FragmentHomeBinding
import dev.brunoribeiro.ajuste.entities.Task
import dev.brunoribeiro.ajuste.repository.ServiceRepository


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var repository: ServiceRepository

    val viewModel by viewModels<HomeViewModel>(){
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(repository) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
        repository = ServiceRepository.getInstance(requireContext())

        viewModel.getTasks()

        binding.fabAddTask.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_taskAddFragment) }

        viewModel.allTasks.observe(viewLifecycleOwner){
            binding.rvTasks.apply {
                adapter = TasksAdapter(it)
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }

        return binding.root
    }


}