package com.godslew.tlaloc.core.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface FeatureFlagRepositoryType {
  fun isFeature1Enabled(): Flow<Boolean>
  fun isFeature2Enabled(): Flow<Boolean>
}
