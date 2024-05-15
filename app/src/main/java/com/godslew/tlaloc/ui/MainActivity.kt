package com.godslew.tlaloc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  internal lateinit var featureFlagRepository: FeatureFlagRepositoryType

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
  }
}
