package com.example.opendesa.ui.keluhan.screen.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.domain.usecase.ComplaintUseCase
import com.example.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
  @Named("deviceUid") private val deviceUid: String,
  private val complaintUseCase: ComplaintUseCase
): BaseViewModel() {

  private var _detailState: MutableStateFlow<DetailScreenState> = MutableStateFlow(DetailScreenState.Empty)
  val detailState: StateFlow<DetailScreenState> = _detailState

  private var _generatedCaptcha = mutableStateOf("")
  val generatedCaptcha: State<String> = _generatedCaptcha

  init {
    generateCaptcha(6)
  }

  fun postComplaint(complaintRequest: ComplaintRequest, captcha: String) {
    viewModelScope.launch {
      _detailState.value = DetailScreenState.Loading

      if(complaintRequest.nik.isEmpty() || complaintRequest.birthDate.isEmpty() || complaintRequest.category.isEmpty() || complaintRequest.report.isEmpty() || complaintRequest.title.isEmpty()) {
        _detailState.value = DetailScreenState.Error(
          isImportant = false,
          message = "Semua field harus diisi!"
        )
      }
      else if(captcha != generatedCaptcha.value) {
        _detailState.value = DetailScreenState.Error(
          isImportant = false,
          message = "Kode verifikasi tidak valid!"
        )
      }
      else {
        complaintUseCase.postComplaint(complaintRequest.copy(deviceUid = deviceUid)).collect { result ->
          when(result) {
            is Resource.Success -> {
              _detailState.value = DetailScreenState.Success(
                data = result.value
              )
            }
            is Resource.Failure -> {
              _detailState.value = DetailScreenState.Error(
                isImportant = true,
                message = result.errorMessage.orEmpty()
              )
            }
            else -> Unit
          }
        }
      }
    }
  }

  fun generateCaptcha(length: Int) {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    _generatedCaptcha.value =  (1..length)
      .map { allowedChars.random() }
      .joinToString("")
  }

}