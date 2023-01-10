package com.example.opendesa.ui.keluhan.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.opendesa.R
import com.example.opendesa.theme.Orange
import com.example.opendesa.ui.keluhan.Router
import com.example.opendesa.ui.keluhan.component.pre.ErrorItem
import com.example.opendesa.ui.keluhan.component.pre.LoadingItem

@ExperimentalMaterialApi @ExperimentalMaterial3Api @Composable fun ListScreen(
  navController: NavHostController, viewModel: ListViewModel = hiltViewModel()
) {
  val state: ListScreenState by viewModel.listState
  val listState = rememberLazyListState()
  // The FAB is initially expanded. Once the first visible item is past the first item we
  // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
  val expandedFab by remember {
    derivedStateOf {
      listState.firstVisibleItemIndex == 0
    }
  }

  navController.GetOnceResult<Boolean>(keyResult = "isPostSuccess") {
    viewModel.getComplaints()
  }

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .background(Color.White),
    floatingActionButton = {
    ExtendedFloatingActionButton(
      onClick = { navController.navigate(Router.DETAIL) },
      expanded = expandedFab,
      icon = { Icon(
        painter = painterResource(id = R.drawable.ic_send),
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(24.dp)
      ) },
      text = { Text(text = stringResource(R.string.send_complaint), color = Color.White) },
      containerColor = Orange
    )
  }) { contentPadding ->
    when {
      state.isLoading -> {
        LoadingItem(
          modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
        )
      }
      state.hasError -> {
        ErrorItem(
          message = state.errorMessage.orEmpty(),
          onButtonClick = { viewModel.getComplaints() },
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        )
      }
      else -> {
        if (state.data.isNullOrEmpty()) {
          ErrorItem(
            imageRes = R.drawable.not_found,
            title = stringResource(R.string.empty_data),
            message = stringResource(R.string.empty_desc),
            modifier = Modifier
              .fillMaxSize()
              .padding(horizontal = 16.dp)
          )
        } else {
          LazyColumn(
            state = listState,
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(state.data!!.reversed()) { complaint ->
              ComplaintItem(complaint = complaint, onSwipeDelete = { complaint ->
                viewModel.removeComplaint(complaint.id.toString())
              })
            }
          }
        }
      }
    }
  }
}

@Composable fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit) {
  val valueScreenResult =
    currentBackStackEntry?.savedStateHandle?.getStateFlow<T?>(keyResult, null)?.collectAsState()

  valueScreenResult?.value?.let {
    onResult(it)

    currentBackStackEntry?.savedStateHandle?.remove<T>(keyResult)
  }
}