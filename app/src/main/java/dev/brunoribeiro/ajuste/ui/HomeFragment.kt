package dev.brunoribeiro.ajuste.ui

import android.database.DataSetObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.brunoribeiro.ajuste.R
import dev.brunoribeiro.ajuste.adapters.TasksAdapter
import dev.brunoribeiro.ajuste.databinding.FragmentHomeBinding
import dev.brunoribeiro.ajuste.entities.SwipeToDeleteCallback
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

        val swipeHandler = object : SwipeToDeleteCallback(binding.root.context) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.rvTasks.adapter as TasksAdapter
                adapter.removeAt(viewHolder.adapterPosition, binding.root)
            }
        }


        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvTasks)

        viewModel.allTasks.observe(viewLifecycleOwner){
            binding.rvTasks.apply {
                adapter = TasksAdapter(it, viewModel)
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTasks()
    }


}