package com.example.opendesa.api

import com.example.opendesa.model.Berita
import com.example.opendesa.model.Profil
import retrofit2.http.GET

interface OpenDesaApi {
    @GET("api/berita-desa")
    suspend fun getBerita(): List<List<Berita>>

    @GET("api/berita-desa/search")
    suspend fun getSearch(): List<List<Berita>>

    @GET("api/kecamatan/1")
    suspend fun getProfil(): Profil
}