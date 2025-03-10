package com.darleyleal.consultordecpf.data.repository

import com.darleyleal.consultordecpf.data.model.ZipCodeResponse
import com.darleyleal.consultordecpf.data.network.ZipCodeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ZipCodeRepository {
    suspend fun getZipCode(cep: String): Response<ZipCodeResponse> {
        return withContext(Dispatchers.IO) {
            ZipCodeApi.zipCodeApiService.getZipCode(cep)
        }
    }
}