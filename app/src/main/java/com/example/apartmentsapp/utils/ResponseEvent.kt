package com.example.apartmentsapp.utils

import com.example.apartmentsapp.model.ApartmentInfo

sealed class ResponseEvent(val isLoading: Boolean) {
    class Success(val resultList: List<ApartmentInfo.Content>, isLoading: Boolean = false): ResponseEvent(isLoading)
    class Error(val errorBody: String, isLoading: Boolean = false): ResponseEvent(isLoading)
    class InProcess(isLoading: Boolean = true): ResponseEvent(isLoading)
}