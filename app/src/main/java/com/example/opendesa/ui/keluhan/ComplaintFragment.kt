package com.example.opendesa.ui.keluhan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import com.example.opendesa.ui.keluhan.screen.create.DetailScreen
import com.example.opendesa.ui.keluhan.screen.list.ListScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi @ExperimentalPermissionsApi @ExperimentalMaterial3Api @ExperimentalMaterialApi @ExperimentalAnimationApi @AndroidEntryPoint
class ComplaintFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        val navController = rememberAnimatedNavController()

        AnimatedNavHost(navController = navController, startDestination = Router.LIST) {
          composable(route = Router.LIST, enterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300)
            )
          }, exitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300)
            )
          }, popEnterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(300)
            )
          }, popExitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(300)
            )
          }) {
            ListScreen(navController = navController)
          }

          composable(route = Router.DETAIL, enterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300)
            )
          }, exitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300)
            )
          }, popEnterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(300)
            )
          }, popExitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(300)
            )
          }) {
            DetailScreen(navController = navController)
          }
        }
      }
    }
  }

}