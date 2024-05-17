package com.godslew.tlaloc.feature.home.ui

import androidx.lifecycle.ViewModel
import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
  @Inject
  constructor(
    featureFlagRepository: FeatureFlagRepositoryType,
  ) : ViewModel()
