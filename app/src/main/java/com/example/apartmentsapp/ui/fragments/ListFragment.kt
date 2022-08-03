package com.example.apartmentsapp.ui.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apartmentsapp.R
import com.example.apartmentsapp.databinding.FragmentListBinding
import com.example.apartmentsapp.ui.adapters.ApartmentAdapter
import com.example.apartmentsapp.viewmodel.ListViewModel
import kotlinx.coroutines.flow.collect
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

        observers()

        onClickListeners()

    }

    private fun init() {
        binding.root.apply {
            adapter = apartmentAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.getApartments()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.apartmentList.collect {
                    apartmentAdapter.submitList(it)
                }
            }
        }
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