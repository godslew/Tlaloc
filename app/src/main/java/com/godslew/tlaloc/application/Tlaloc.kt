package com.godslew.tlaloc.application

import android.app.Application
import application.Session
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Tlaloc : Application() {
  @Inject
  internal lateinit var session: Session

  override fun onCreate() {
    super.onCreate()
    session.initialize()
  }

  override fun onTerminate() {
    super.onTerminate()
    session.onStop()
  }
}
