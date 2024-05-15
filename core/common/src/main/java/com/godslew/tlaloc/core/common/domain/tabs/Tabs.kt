package com.godslew.tlaloc.core.common.domain.tabs

import kotlinx.serialization.Serializable

@Serializable
sealed class Tabs {

  @Serializable
  data object Home : Tabs()
  @Serializable
  data object Favorite : Tabs()
  @Serializable
  data object Gate : Tabs()
  @Serializable
  data object Event : Tabs()
  @Serializable
  data object Profile : Tabs()
}
