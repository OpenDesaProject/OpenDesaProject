package com.example.opendesa.ui.keluhan.screen.list

import com.example.core.data.source.remote.model.response.Complaint

data class ListScreenState(
  val data: List<Complaint>? = null,
  val isLoading: Boolean = false,
  val hasError: Boolean = false,
  val errorMessage: String? = null
)