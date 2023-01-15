package com.example.presentation.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.ui.theme.MastadonTheme

@Composable
fun Post(modifier: Modifier = Modifier) {
  Column(modifier = modifier.padding(16.dp)) {
    Row {
      AsyncImage(
        model = "https://cdn.masto.host/androiddevsocial/accounts/avatars/109/499/015/925/975/175/original/970ffadbe51dc125.jpg",
        //error = debugPlaceholder(R.drawable.placeholder),
        placeholder = debugPlaceholder(R.drawable.placeholder),
        contentDescription = null,
        modifier = Modifier
          .size(64.dp)
          .clip(MaterialTheme.shapes.small)
      )
      Spacer(modifier = Modifier.width(16.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = "Josh",
          style = MaterialTheme.typography.titleMedium
        )
        Text(
          text = "@JoshEldridge@androiddev.social * 3h"
        )
      }
      Column {
        IconButton(onClick = { /*TODO*/ }) {
          Image(
            imageVector = Icons.Default.Settings,
            contentDescription = "Post options.",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
          )
        }
        IconButton(onClick = { /*TODO*/ }) {
          Image(
            imageVector = Icons.Default.VisibilityOff,
            contentDescription = "Tap to reveal image.",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
          )
        }
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
      Post(modifier = Modifier.fillMaxSize())
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