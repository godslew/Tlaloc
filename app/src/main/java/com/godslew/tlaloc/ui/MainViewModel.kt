package com.godslew.tlaloc.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import com.godslew.tlaloc.core.common.domain.tabs.Tabs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel
  @Inject
  constructor(
    featureFlagRepository: FeatureFlagRepositoryType,
  ) : ViewModel() {
    private val selectedTab = MutableStateFlow<Tabs>(Tabs.Home)

    val uiState: StateFlow<UiState> =
      combine(
        featureFlagRepository.isFeature1Enabled(),
        featureFlagRepository.isFeature2Enabled(),
        selectedTab,
      ) { isFeature1Enabled, isFeature2Enabled, selectedTab ->
        UiState(
          selectedTab = selectedTab,
          isFeature1Enabled = isFeature1Enabled,
          isFeature2Enabled = isFeature2Enabled,
        )
      }.stateIn(
        scope = viewModelScope,
        initialValue = UiState(),
        started = SharingStarted.WhileSubscribed(5_000),
      )

    fun onSelectedTab(tab: Tabs) {
      selectedTab.update { tab }
    }
  }

data class UiState(
  val selectedTab: Tabs = Tabs.Home,
  val isFeature1Enabled: Boolean = false,
  val isFeature2Enabled: Boolean = false,
)
