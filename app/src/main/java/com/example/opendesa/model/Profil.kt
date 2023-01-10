package com.example.opendesa.model

import com.google.gson.annotations.SerializedName

data class Profil (
    val profil : ProfilDetail
        )
data class ProfilDetail(
    val visi:String,
    val misi:String,
    @SerializedName("sambutan_camat")
    val sambutanCamat:String,
    @SerializedName("photo_camat")
    val photoCamat:String,
    val camat:String,
    val sekretaris:String,
    val kepsek_pemerintahan_umum:String,
    val kepsek_kesejahteraan_masyarakat:String,
    val kepsek_pemberdayaan_masyarakat:String,
    val kepsek_pelayanan_umum:String,
    val kepsek_trantib:String
)