package com.example.opendesa.ui.profildesa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opendesa.repository.Repository
import kotlinx.coroutines.launch

class VisiMisiViewModel(private val repository: Repository) : ViewModel() {
    private val _visi = MutableLiveData<String>()
    val visi : LiveData<String> = _visi

    private val _misi = MutableLiveData<String>()
    val misi : LiveData<String> = _misi

    private val _sambutanCamat = MutableLiveData<String>()
    val sambutanCamat : LiveData<String> = _sambutanCamat

    private val _photoCamat = MutableLiveData<String>()
    val photoCamat : LiveData<String> = _photoCamat

    private val _camat = MutableLiveData<String>()
    val camat : LiveData<String> = _camat

    private val _sekretaris = MutableLiveData<String>()
    val sekretaris : LiveData<String> = _sekretaris

    private val _kepsekPemerintahanUmum = MutableLiveData<String>()
    val kepsekPemerintahanUmum : LiveData<String> = _kepsekPemerintahanUmum

    private val _kepsekKesejahteraanMasyarakat = MutableLiveData<String>()
    val kepsekKesejahteraanMasyarakat : LiveData<String> = _kepsekKesejahteraanMasyarakat

    private val _kepsekPemberdayaanMasyarakat = MutableLiveData<String>()
    val kepsekPemberdayaanMasyarakat : LiveData<String> = _kepsekPemberdayaanMasyarakat

    private val _kepsekPelayananUmum = MutableLiveData<String>()
    val kepsekPelayananUmum : LiveData<String> = _kepsekPelayananUmum

    private val _kepsekTrantib = MutableLiveData<String>()
    val kepsekTrantib : LiveData<String> = _kepsekTrantib

    fun getProfil() {
        viewModelScope.launch {
            val response = repository.getProfile()
            _visi.value = response.profil.visi
            _misi.value = response.profil.misi
            _sambutanCamat.value = response.profil.sambutanCamat
            _photoCamat.value = response.profil.photoCamat
            _camat.value = response.profil.camat
            _sekretaris.value = response.profil.sekretaris
            _kepsekPemerintahanUmum.value = response.profil.kepsek_pemerintahan_umum
            _kepsekKesejahteraanMasyarakat.value = response.profil.kepsek_kesejahteraan_masyarakat
            _kepsekPemberdayaanMasyarakat.value = response.profil.kepsek_pemberdayaan_masyarakat
            _kepsekPelayananUmum.value = response.profil.kepsek_pelayanan_umum
            _kepsekTrantib.value = response.profil.kepsek_trantib
        }
    }
}