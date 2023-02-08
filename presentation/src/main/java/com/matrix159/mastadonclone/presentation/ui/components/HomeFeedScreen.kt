package com.matrix159.mastadonclone.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedPost
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedState

@Composable
fun HomeFeedScreen(
  state: HomeFeedState,
  loadNew: () -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
  ) {
    items(state.homeFeedPosts) { post ->
      Post(
        homeFeedPost = post,
        modifier = Modifier
          .padding(16.dp, 16.dp, 16.dp, 0.dp)
          .clickable { loadNew() }
      )
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
      HomeFeedScreen(
        state = HomeFeedState(
          homeFeedPosts = listOf(
            //TODO get some good test data for an Account object (or just delete these previews).
//            HomeFeedPost("Test author", "Test description", avatar = ""),
//            HomeFeedPost("Another author", "Another description", avatar = "")
          )
        ),
        loadNew = {}
      )
    }
  }
}
