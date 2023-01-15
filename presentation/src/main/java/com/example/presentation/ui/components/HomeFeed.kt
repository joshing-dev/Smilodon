package com.example.presentation.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.ui.theme.MastadonTheme

@Composable
fun HomeFeed(modifier: Modifier = Modifier) {
    val posts by remember { mutableStateOf(listOf("Post 1", "Post 2")) }
    LazyColumn(state = rememberLazyListState()) {
        items(posts) { content ->
            Post()
        }
    }
}

@Preview
@Composable
fun HomeFeedPreview() {
    MastadonTheme {
        HomeFeed()
    }
}