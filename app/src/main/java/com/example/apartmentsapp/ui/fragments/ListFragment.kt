package com.example.apartmentsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apartmentsapp.databinding.FragmentListBinding
import com.example.apartmentsapp.model.ApartmentInfo
import com.example.apartmentsapp.ui.adapters.ApartmentAdapter
import com.example.apartmentsapp.utils.ResponseEvent
import com.example.apartmentsapp.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val apartmentAdapter by lazy { ApartmentAdapter() }

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        onClickListeners()

        observers()

    }

    private fun init() {
        binding.recyclerView.apply {
            adapter = apartmentAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.getApartments()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when(event) {
                        is ResponseEvent.Success -> handleSuccess(event.resultList)
                        is ResponseEvent.Error -> handleError(event.errorBody)
                        else -> {}
                    }
                    binding.progressBar.isVisible = event.isLoading
                }
            }
        }
    }

    private fun handleError(errorBody: String) {
        Snackbar.make(binding.root, errorBody, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleSuccess(resultList: List<ApartmentInfo.Content>) {
        apartmentAdapter.submitList(resultList)
    }

    private fun onClickListeners() {
        apartmentAdapter.onItemClickListener = {
            findNavController().navigate(ListFragmentDirections.toDetailsFragment(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}