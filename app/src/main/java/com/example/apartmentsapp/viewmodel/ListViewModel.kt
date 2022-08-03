package com.example.apartmentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apartmentsapp.model.ApartmentInfo
import com.example.apartmentsapp.model.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val _apartmentsList = MutableStateFlow<List<ApartmentInfo.Content>>(listOf())
    val apartmentList get() = _apartmentsList.asStateFlow()

    fun getApartments() {
        viewModelScope.launch {
            val response = RetrofitInstance.getApartmentApi().getApartments()
            if (response.isSuccessful) {
                _apartmentsList.emit(response.body()?.content ?: emptyList())
            }
        }
    }
}