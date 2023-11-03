package com.andricohalim.jetvalorantskin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.andricohalim.jetvalorantskin.R
import com.andricohalim.jetvalorantskin.ui.theme.JetValorantSkinTheme

@Composable
fun SkinItems(
    photoUrl: String,
    name: String,
    requiredVP: Int,
    modifier: Modifier = Modifier,
    backgroundContentColor: Color = MaterialTheme.colorScheme.tertiary
) {
    Column(
        modifier = modifier
            .background(backgroundContentColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = stringResource(R.string.skin_picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(0.dp))
        )
        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Price: $requiredVP VP",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 15.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
@Preview(showBackground = false)
fun SkinItemPreview() {
    JetValorantSkinTheme {
        SkinItems(
            "",
            "Prime Bundle",
            7100
        )
    }
}