package com.darleyleal.consultordecpf.data.model

import com.google.gson.annotations.SerializedName

data class ZipCodeResponse(
    @SerializedName("bairro") val bairro: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("complemento") val complemento: String,
    @SerializedName("ddd") val ddd: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("gia") val gia: String,
    @SerializedName("ibge") val ibge: String,
    @SerializedName("localidade") val localidade: String,
    @SerializedName("logradouro") val logradouro: String,
    @SerializedName("regiao") val regiao: String,
    @SerializedName("siafi") val siafi: String,
    @SerializedName("uf") val uf: String,
    @SerializedName("unidade") val unidade: String
)