package com.godslew.tlaloc.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Tlaloc : Application() {
  override fun onCreate() {
    super.onCreate()
    // ToDo start up process
  }
}
