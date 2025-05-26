package com.czertilla.gbas.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.czertilla.gbas.R
import com.czertilla.gbas.domain.adapter.ServiceAdapter
import com.czertilla.gbas.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val adapter = ServiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.service_grid)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = adapter
        swipeRefreshLayout = binding.swipeRefreshLayout

        viewModel.services.observe(viewLifecycleOwner) { services ->
            adapter.submitList(services)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshServices()
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) { value ->
            swipeRefreshLayout.isRefreshing = value
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
