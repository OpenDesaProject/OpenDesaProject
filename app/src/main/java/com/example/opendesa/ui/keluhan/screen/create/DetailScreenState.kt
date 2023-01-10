package com.example.opendesa.ui.keluhan.screen.create

import com.example.core.data.source.remote.model.response.Complaint

sealed class DetailScreenState {
  class Success(val data: Complaint): DetailScreenState()
  object Loading: DetailScreenState()
  object Empty: DetailScreenState()
  class Error(val isImportant: Boolean, val message: String): DetailScreenState()
}