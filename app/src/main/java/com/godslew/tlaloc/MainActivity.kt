package com.godslew.tlaloc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godslew.tlaloc.designsystem.theme.TlalocTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      TlalocTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        // A surface container using the 'background' color from the theme
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
          topBar = {
            TopAppBar(
              modifier = Modifier
                .statusBarsPadding(),
              title = {
                Text(
                  text = "Tlaloc",
                )
              },
              colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
              ),
              windowInsets = WindowInsets(0.dp),
              scrollBehavior = scrollBehavior,
            )
          },
          contentWindowInsets = WindowInsets(0.dp)
        ) {
          LazyColumn(
            modifier = Modifier
              .fillMaxSize(),
            contentPadding = it,
            horizontalAlignment = Alignment.CenterHorizontally,
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
    modifier = modifier.padding(12.dp),
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
      TitleAndButton(title = "Number::1")
    }
  }
}
