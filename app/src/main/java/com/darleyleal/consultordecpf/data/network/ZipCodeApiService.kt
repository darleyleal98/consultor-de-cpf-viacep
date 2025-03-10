package com.darleyleal.consultordecpf.data.network

import com.darleyleal.consultordecpf.data.model.ZipCodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ZipCodeApiService {
    @GET("{cep}/json/")
    suspend fun getZipCode(@Path("cep") cep: String): Response<ZipCodeResponse>
}