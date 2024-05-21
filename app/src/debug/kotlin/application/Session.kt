package application

import com.godslew.tlaloc.application.MainSession
import handler.DebugHandler
import javax.inject.Inject

class Session
  @Inject
  constructor(
    private val debugHandler: DebugHandler,
  ) : MainSession() {
    override fun initialize() {
      super.initialize()
      debugHandler.initialize()
    }

    override fun onStop() {
      super.onStop()
      debugHandler.onStop()
    }
  }
