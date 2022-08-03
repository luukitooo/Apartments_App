package com.example.apartmentsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apartmentsapp.R
import com.example.apartmentsapp.databinding.FragmentDetailsBinding
import com.example.apartmentsapp.model.ApartmentInfo

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() = with(binding) {
        val apartment = args.apartment as ApartmentInfo.Content
        Glide.with(apartmentImageView)
            .load(apartment.cover)
            .into(apartmentImageView)
        titleTextView.text = apartment.titleKA
        descriptionTextView.text = apartment.descriptionKA
        dateTextView.text = apartment.publishDate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}