package application

import com.godslew.tlaloc.application.MainSession
import javax.inject.Inject

class Session
  @Inject
  constructor() : MainSession() {
    override fun initialize() {
      super.initialize()
      println("initialize release")
    }
  }
