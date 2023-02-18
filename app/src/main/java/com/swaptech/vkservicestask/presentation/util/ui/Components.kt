package com.swaptech.vkservicestask.presentation.util.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.swaptech.vkservicestask.R
import com.swaptech.vkservicestask.domain.service.Service

@Composable
fun Placeholder(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
fun ErrorPlaceHolder() {
    Placeholder(
        content = {
            Text(text = stringResource(R.string.error_to_fetch_data))
        }
    )
}

@Composable
fun FatalErrorPlaceholder() {
    Placeholder(
        content = {
            Text(text = stringResource(R.string.check_your_internet))
        }
    )
}

@Composable
fun LoadingPlaceholder() {
    Placeholder {
        CircularProgressIndicator()
    }
}

@Composable
fun ServiceItem(
    onClick: () -> Unit,
    service: Service
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clickable(
                onClick = onClick
            )
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = ImageRequest.Builder(context)
                .data(service.iconUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_image_24),
            error = painterResource(R.drawable.baseline_image_24),
            fallback = painterResource(R.drawable.baseline_image_24),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = service.name,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceItem_Preview() {
    ServiceItem(
        onClick = {  },
        service = Service("test", "", "", "")
    )
}
