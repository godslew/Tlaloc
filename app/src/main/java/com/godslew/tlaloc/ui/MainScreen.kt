package com.godslew.tlaloc.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
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
import com.godslew.tlaloc.core.common.domain.tabs.Tabs
import com.godslew.tlaloc.feature.home.ui.HomeScreen

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
      composable<Tabs.Home> {
        HomeScreen(
          navController = navController,
        )
      }
      composable<Tabs.Favorite> { }
      composable<Tabs.Event> { }
      composable<Tabs.Profile> { }
    }
  }
}

@Composable
private fun MainBottomBar(
  navController: NavController,
  selectedTab: Tabs,
  modifier: Modifier = Modifier,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBar(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.background,
    tonalElevation = 0.dp,
  ) {
    HomeTab(
      navController = navController,
      selected = selectedTab is Tabs.Home,
      onSelectedTab = onSelectedTab,
    )
    FavoriteTab(
      navController = navController,
      selected = selectedTab is Tabs.Favorite,
      onSelectedTab = onSelectedTab,
    )
    GateTab(
      navController = navController,
    )
    EventTab(
      navController = navController,
      selected = selectedTab is Tabs.Event,
      onSelectedTab = onSelectedTab,
    )
    ProfileTab(
      navController = navController,
      selected = selectedTab is Tabs.Profile,
      onSelectedTab = onSelectedTab,
    )
  }
}

@Composable
private fun RowScope.HomeTab(
  navController: NavController,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBarItem(
    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
    label = { Text(stringResource(com.godslew.tlaloc.core.common.R.string.tab_home_title)) },
    selected = selected,
    onClick = {
      onSelectedTab(Tabs.Home)
      navController.navigate(Tabs.Home) {
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

@Composable
private fun RowScope.FavoriteTab(
  navController: NavController,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBarItem(
    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
    label = { Text(stringResource(com.godslew.tlaloc.core.common.R.string.tab_favorite_title)) },
    selected = selected,
    onClick = {
      onSelectedTab(Tabs.Favorite)
      navController.navigate(Tabs.Favorite) {
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

@Composable
private fun RowScope.EventTab(
  navController: NavController,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBarItem(
    icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
    label = { Text(stringResource(com.godslew.tlaloc.core.common.R.string.tab_event_title)) },
    selected = selected,
    onClick = {
      onSelectedTab(Tabs.Event)
      navController.navigate(Tabs.Event) {
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

@Composable
private fun RowScope.ProfileTab(
  navController: NavController,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onSelectedTab: (Tabs) -> Unit = {},
) {
  NavigationBarItem(
    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
    label = { Text(stringResource(com.godslew.tlaloc.core.common.R.string.tab_profile_title)) },
    selected = selected,
    onClick = {
      onSelectedTab(Tabs.Profile)
      navController.navigate(Tabs.Profile) {
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

@Composable
private fun RowScope.GateTab(
  navController: NavController,
  modifier: Modifier = Modifier,
) {
  NavigationBarItem(
    icon = {
      Icon(
        Icons.Outlined.AddCircle,
        contentDescription = null,
      )
    },
    selected = false,
    onClick = {
      navController.navigate(Tabs.Home) {
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
