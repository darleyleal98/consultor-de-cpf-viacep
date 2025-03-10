package com.darleyleal.consultordecpf.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.consultordecpf.data.model.ZipCodeResponse
import com.darleyleal.consultordecpf.data.repository.ZipCodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ZipCodeViewModel : ViewModel() {
    private val repository = ZipCodeRepository()

    private val _zipCodeResponse = MutableStateFlow<Response<ZipCodeResponse>?>(null)
    val zipCodeResponse = _zipCodeResponse.asStateFlow()

    fun getZipCode(cep: String) {
        viewModelScope.launch {
            _zipCodeResponse.value = repository.getZipCode(cep)
        }
    }

    fun clear() {
        _zipCodeResponse.value = null
    }
}