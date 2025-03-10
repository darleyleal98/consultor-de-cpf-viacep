package com.darleyleal.consultordecpf.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://viacep.com.br/ws/"

object ZipCodeApi {
    val zipCodeApiService: ZipCodeApiService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(ZipCodeApiService::class.java)
    }
}