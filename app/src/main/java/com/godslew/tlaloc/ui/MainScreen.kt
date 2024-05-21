package com.godslew.tlaloc.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.godslew.tlaloc.feature.home.ui.HomeScreen
import com.godslew.tlaloc.value.Tabs
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val navController = rememberNavController()
  Scaffold(
    bottomBar = {
      MainBottomBar(
        navController = navController,
        tabs = uiState.tabs,
        selectedTab = uiState.selectedTab,
        onSelectedTab = viewModel::onSelectedTab,
      )
    },
    modifier = modifier,
  ) { paddingValues ->
    NavHost(
      navController = navController,
      startDestination = Tabs.Home,
      modifier = Modifier.padding(paddingValues),
    ) {
      composable<Tabs.Home> { HomeScreen() }
      composable<Tabs.Favorite> { }
      composable<Tabs.Event> { }
      composable<Tabs.Profile> { }
    }
  }
}

@Composable
private fun MainBottomBar(
  navController: NavController,
  tabs: ImmutableList<Tabs>,
  selectedTab: Tabs,
  modifier: Modifier = Modifier,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBar(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.background,
    tonalElevation = 0.dp,
  ) {
    tabs.forEach { tab ->
      Tab(
        navController = navController,
        tab = tab,
        selected = selectedTab == tab,
        onSelectedTab = onSelectedTab,
      )
    }
  }
}

@Composable
fun RowScope.Tab(
  navController: NavController,
  tab: Tabs,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onSelectedTab: (tab: Tabs) -> Unit = {},
) {
  NavigationBarItem(
    icon = { Icon(tab.icon, contentDescription = null) },
    label = { Text(stringResource(tab.title)) },
    selected = selected,
    onClick = {
      onSelectedTab(tab)
      navController.navigate(tab) {
        popUpTo(navController.graph.findStartDestination().id) {
          saveState = true
        }
        launchSingleTop = true
        restoreState = true
      }
    },
    modifier = modifier,
  )
}
