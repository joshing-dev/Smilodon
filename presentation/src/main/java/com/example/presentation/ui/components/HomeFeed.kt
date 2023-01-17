package com.example.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.MastadonTheme

@Composable
fun HomeFeed(modifier: Modifier = Modifier) {
  val posts by remember { mutableStateOf(listOf("Post 1", "Post 2")) }
  LazyColumn(
    state = rememberLazyListState(),
    modifier = modifier
  ) {
    items(posts) { content ->
      Post(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp))
      Divider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant
      )
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeFeedPreview() {
  MastadonTheme {
    Surface {
      HomeFeed()
    }
  }
}