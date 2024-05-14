package com.godslew.tlaloc.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
) {
  val navController = rememberNavController()
  Scaffold(
    modifier = modifier,
    bottomBar = {

    },
  ) {

  }
}
