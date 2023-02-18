package com.swaptech.vkservicestask.presentation.screens.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.swaptech.vkservicestask.R
import com.swaptech.vkservicestask.presentation.util.ext.findActivity

@Composable
fun DetailScreen(
    serviceName: String,
    serviceDescription: String,
    serviceIconUrl: String,
    serviceUrl: String,
    onBackNavButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detail)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackNavButtonClicked
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(128.dp),
                model = ImageRequest.Builder(context)
                    .data(serviceIconUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.baseline_image_24),
                error = painterResource(R.drawable.baseline_image_24),
                fallback = painterResource(R.drawable.baseline_image_24),
                contentDescription = null
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = serviceName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Text(
                text =serviceDescription
            )
            Text(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            val intent = Intent().apply {
                                action = Intent.ACTION_VIEW
                                data = Uri.parse(serviceUrl)
                            }
                            context.findActivity().startActivity(intent)
                        }
                    )
                    .align(Alignment.Start),
                text = serviceUrl,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview
@Composable
fun DetailScreen_Preview() {
    DetailScreen(
        serviceName = "Vk",
        serviceDescription = "Social network",
        serviceIconUrl = "https://cdn.some-url.com",
        serviceUrl = "https://some-url.com",
        onBackNavButtonClicked = {

        }
    )
}
