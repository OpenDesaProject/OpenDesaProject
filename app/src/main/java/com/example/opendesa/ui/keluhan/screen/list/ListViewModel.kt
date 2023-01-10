package com.example.opendesa.ui.keluhan.screen.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.domain.usecase.ComplaintUseCase
import com.example.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ListViewModel @Inject constructor(
  @Named("deviceUid") private val deviceUid: String,
  private val complaintUseCase: ComplaintUseCase
): BaseViewModel() {

  private var _listState = mutableStateOf(ListScreenState())
  val listState: State<ListScreenState> = _listState

  init {
    getComplaints()
  }

  fun getComplaints() {
    viewModelScope.launch {
      _listState.value = ListScreenState(
        isLoading = true
      )

      complaintUseCase.getComplaintByUid(deviceUid).collect { result ->
        when(result) {
          is Resource.Success -> {
            _listState.value = ListScreenState(
              data = result.value
            )
          }
          is Resource.Failure -> {
            _listState.value = ListScreenState(
              hasError = true,
              errorMessage = result.errorMessage
            )
          }
          else -> Unit
        }
      }
    }
  }

  fun removeComplaint(id: String) {
    viewModelScope.launch {
      _listState.value = _listState.value.copy(
        isLoading = true
      )

      complaintUseCase.removeComplaintById(id).collect { result ->
        when(result) {
          is Resource.Success -> {
            _listState.value = _listState.value.copy(
              data = _listState.value.data?.filter { it.id != id.toInt() },
              isLoading = false
            )
          }
          is Resource.Failure -> {
            _listState.value = ListScreenState(
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