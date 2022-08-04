package com.example.apartmentsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apartmentsapp.model.ApartmentInfo
import com.example.apartmentsapp.model.service.RetrofitInstance
import com.example.apartmentsapp.utils.ResponseEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val _events = MutableStateFlow<ResponseEvent>(ResponseEvent.InProcess())
    val events: StateFlow<ResponseEvent> get() = _events

    fun getApartments() {
        viewModelScope.launch {
            val response = RetrofitInstance.getApartmentApi().getApartments()
            if (response.isSuccessful) {
                _events.emit(ResponseEvent.Success(response.body()?.content ?: emptyList()))
            } else {
                _events.emit(ResponseEvent.Error(response.errorBody().toString()))
            }
        }
    }
}