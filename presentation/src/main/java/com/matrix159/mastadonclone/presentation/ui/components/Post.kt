package com.matrix159.mastadonclone.presentation.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix159.mastadonclone.presentation.R
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedPost

@Composable
fun Post(homeFeedPost: HomeFeedPost, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      AsyncImage(
        model = "https://cdn.masto.host/androiddevsocial/accounts/avatars/109/499/015/925/975/175/" +
          "original/970ffadbe51dc125.jpg",
        placeholder = debugPlaceholder(R.drawable.placeholder),
        contentDescription = null,
        modifier = Modifier
          .size(48.dp)
          .clip(MaterialTheme.shapes.small)
      )
      Spacer(modifier = Modifier.width(16.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = homeFeedPost.author,
          style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "@JoshEldridge@androiddev.social · 3h",
          color = MaterialTheme.colorScheme.tertiary,
          style = MaterialTheme.typography.titleSmall,
          maxLines = 1,
        )
      }
      Column {
        IconButton(onClick = { /*TODO*/ }) {
          Image(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "Post options.",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Box {
      Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum quis eros " +
        "pellentesque, malesuada purus sit amet, ultrices lectus. Duis in pulvinar metus. Nullam " +
        "quis magna vulputate, viverra nisl nec, dictum felis. Proin facilisis magna eu est " +
        "pellentesque dictum. Donec at fermentum nunc. Suspendisse potenti. In eu pharetra urna." +
        " Sed ut tortor maximus quam lobortis feugiat eget nec turpis. Vestibulum ut lacus at magna " +
        "ornare eleifend. Duis in odio elementum elit pharetra fermentum. In venenatis sed dui nec " +
        "cursus. Praesent blandit nisl quis elit malesuada sagittis. Ut eleifend felis id arcu " +
        "mollis, sed bibendum nisl laoreet. Maecenas sit amet velit eu. ")
// TODO: Figure out where we want spoiler content action
//      IconButton(
//        onClick = { /*TODO*/ },
//        modifier = Modifier.align(Alignment.TopEnd)
//      ) {
//        Image(
//          imageVector = Icons.Default.VisibilityOff,
//          contentDescription = "Tap to reveal image.",
//          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
//        )
//      }
    }

    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      IconButton(
        onClick = { /*TODO*/ },
      ) {
        Image(
          imageVector = Icons.Outlined.Forum,
          contentDescription = "Reply to post.",
          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
      }
      IconButton(
        onClick = { /*TODO*/ },
      ) {
        Image(
          imageVector = Icons.Outlined.Repeat,
          contentDescription = "Reblog this post.",
          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
      }
      IconButton(
        onClick = { /*TODO*/ },
      ) {
        Image(
          imageVector = Icons.Outlined.StarRate,
          contentDescription = "Favorite this post.",
          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
      }
      IconButton(
        onClick = { /*TODO*/ },
      ) {
        Image(
          imageVector = Icons.Outlined.Share,
          contentDescription = "Share this post.",
          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() {
  MastadonTheme {
    Surface {
      Post(HomeFeedPost("preview author", "preview test"), modifier = Modifier.fillMaxSize())
    }
  }
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
  if (LocalInspectionMode.current) {
    painterResource(id = debugPreview)
  } else {
    null
  }
