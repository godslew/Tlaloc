package com.godslew.tlaloc.value

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Tabs {
  abstract val icon: ImageVector
  abstract val title: Int

  @Serializable
  data object Home : Tabs() {
    override val icon: ImageVector
      get() = Icons.Filled.Home
    override val title: Int
      get() = com.godslew.tlaloc.core.common.R.string.tab_home_title
  }

  @Serializable
  data object Favorite : Tabs() {
    override val icon: ImageVector
      get() = Icons.Filled.Favorite
    override val title: Int
      get() = com.godslew.tlaloc.core.common.R.string.tab_favorite_title
  }

  @Serializable
  data object Event : Tabs() {
    override val icon: ImageVector
      get() = Icons.Filled.DateRange
    override val title: Int
      get() = com.godslew.tlaloc.core.common.R.string.tab_event_title
  }

  @Serializable
  data object Profile : Tabs() {
    override val icon: ImageVector
      get() = Icons.Filled.Person
    override val title: Int
      get() = com.godslew.tlaloc.core.common.R.string.tab_profile_title
  }
}
