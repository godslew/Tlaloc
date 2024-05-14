package com.godslew.tlaloc.domain

import com.godslew.tlaloc.BuildConfig
import com.godslew.tlaloc.core.common.domain.feature.Feature
import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureFlagRepository
  @Inject
  constructor() : FeatureFlagRepositoryType {
    private val flow = MutableStateFlow(Feature.entries.associateWith { BuildConfig.FeatureFlag })

    private fun isEnabled(feature: Feature) = flow.mapNotNull { it[feature] }

    fun update(
      feature: Feature,
      isEnabled: Boolean,
    ) {
      flow.update { map ->
        map.onEach {
          if (it.key == feature) {
            it.key to isEnabled
          } else {
            it.key to it.value
          }
        }
      }
    }

    override fun isFeature1Enabled() = isEnabled(Feature.Feature1)

    override fun isFeature2Enabled() = isEnabled(Feature.Feature2)
  }
