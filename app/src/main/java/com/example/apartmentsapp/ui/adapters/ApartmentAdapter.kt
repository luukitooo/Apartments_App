package com.example.apartmentsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apartmentsapp.databinding.ApartmentItemBinding
import com.example.apartmentsapp.model.ApartmentInfo

class ApartmentAdapter: ListAdapter<ApartmentInfo.Content, ApartmentAdapter.ApartmentViewHolder>(ApartmentItemCallback()) {

    var onItemClickListener: ((ApartmentInfo.Content) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ApartmentViewHolder(
        ApartmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        holder.bind()
    }

    inner class ApartmentViewHolder(private val binding: ApartmentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val apartment = getItem(adapterPosition)
            binding.apply {
                Glide.with(this.apartmentImageView)
                    .load(apartment.cover)
                    .into(apartmentImageView)
                titleTextView.text = apartment.titleKA
                descriptionTextView.text = apartment.descriptionKA.trim()
                dateTextView.text = apartment.publishDate
                this.root.setOnClickListener {
                    onItemClickListener?.invoke(apartment)
                }
            }
        }
    }

    private class ApartmentItemCallback: DiffUtil.ItemCallback<ApartmentInfo.Content>() {
        override fun areItemsTheSame(oldItem: ApartmentInfo.Content, newItem: ApartmentInfo.Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ApartmentInfo.Content, newItem: ApartmentInfo.Content): Boolean {
            return oldItem == newItem
        }

    }
}