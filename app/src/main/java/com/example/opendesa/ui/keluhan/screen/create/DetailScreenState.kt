package com.example.opendesa.ui.keluhan.screen.create

import com.example.core.data.source.remote.model.response.Complaint

data class DetailScreenState(
  val data: Complaint? = null,
  val isSuccess: Boolean = false,
  val isLoading: Boolean = false,
  val hasError: Boolean = false,
  val errorMessage: String? = null
)