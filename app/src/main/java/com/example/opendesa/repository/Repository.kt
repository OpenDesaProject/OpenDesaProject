package com.example.opendesa.repository

import com.example.opendesa.api.RetrofitInstance
import com.example.opendesa.model.Berita
import com.example.opendesa.model.Profil

class Repository {

    suspend fun getBerita(): List<List<Berita>> {
        return RetrofitInstance.api.getBerita()
    }


    suspend fun getSearch(): List<List<Berita>> {
        return RetrofitInstance.api.getSearch()
    }

    suspend fun getProfile(): Profil {
        return RetrofitInstance.api.getProfil()
    }

    suspend fun getUnduhanData(type: String) = RetrofitInstance.api.getUnduhanList(type)

    suspend fun downloadFile(url: String, path: String, fileName: String) {

    }
}