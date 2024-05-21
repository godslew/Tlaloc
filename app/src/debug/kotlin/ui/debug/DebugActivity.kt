package ui.debug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.godslew.tlaloc.designsystem.theme.TlalocTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebugActivity : ComponentActivity() {
  private val viewModel: DebugViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      TlalocTheme {
        DebugScreen()
      }
    }
  }
}
