package com.godslew.tlaloc.di

import com.godslew.tlaloc.core.common.domain.repository.FeatureFlagRepositoryType
import com.godslew.tlaloc.domain.FeatureFlagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {
  @Binds
  abstract fun bindFeatureFlagRepositoryType(repository: FeatureFlagRepository): FeatureFlagRepositoryType
}
