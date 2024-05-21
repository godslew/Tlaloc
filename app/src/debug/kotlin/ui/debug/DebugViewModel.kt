package ui.debug

import androidx.lifecycle.ViewModel
import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DebugViewModel
  @Inject
  constructor(
    private val featureFlagRepository: FeatureFlagRepositoryType,
  ) : ViewModel()
