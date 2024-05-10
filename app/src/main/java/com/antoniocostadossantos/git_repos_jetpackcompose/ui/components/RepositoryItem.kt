package com.antoniocostadossantos.git_repos_jetpackcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.antoniocostadossantos.git_repos_jetpackcompose.model.Item

@Composable
fun RepositoryItem(
    item: Item,
    onClick: (Item) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp)
            .clickable {
                onClick(item)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = item.owner.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
            )
            Text(
                text = item.name,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}