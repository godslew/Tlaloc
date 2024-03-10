package com.godslew.tlaloc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import com.godslew.tlaloc.ui.theme.TlalocTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)
    setContent {
      TlalocTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier
            .fillMaxSize()
        ) {
          LazyColumn(
            modifier = Modifier
              .clipToBounds()
              .fillMaxSize()
          ) {
            items(
              items = (1..100).toList(),
            ) {
              TitleAndButton(
                title = "Number::$it"
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TitleAndButton(
  title: String,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge
    )
  }
}

@Preview
@Composable
private fun TitleAndButtonPreview() {
  TlalocTheme {
    Surface {
      TitleAndButton(title = "1")
    }
  }
}