package com.example.opendesa.ui.keluhan.screen.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.domain.usecase.ComplaintUseCase
import com.example.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
  @Named("deviceUid") private val deviceUid: String,
  private val complaintUseCase: ComplaintUseCase
): BaseViewModel() {

  private var _detailState = mutableStateOf(DetailScreenState())
  val detailState: State<DetailScreenState> = _detailState

  fun postComplaint(complaintRequest: ComplaintRequest) {
    viewModelScope.launch {
      _detailState.value = DetailScreenState(
        isLoading = true
      )

      complaintUseCase.postComplaint(complaintRequest.copy(deviceUid = deviceUid)).collect { result ->
        when(result) {
          is Resource.Success -> {
            _detailState.value = DetailScreenState(
              data = result.value,
              isSuccess = true
            )
          }
          is Resource.Failure -> {
            _detailState.value = DetailScreenState(
              hasError = true,
              errorMessage = result.errorMessage
            )
          }
          else -> Unit
        }
      }
    }
  }

}