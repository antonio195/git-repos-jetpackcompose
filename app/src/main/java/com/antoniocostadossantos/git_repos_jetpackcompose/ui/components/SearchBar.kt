package com.antoniocostadossantos.git_repos_jetpackcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    text: String = "",
    onSearchChange: (String) -> Unit = {},
    onClickSearch: () -> Unit = {},
    labelText: String = "Search"
) {
    OutlinedTextField(
        value = text,
        onValueChange = {
            onSearchChange(it)
        },
        trailingIcon = {
            IconButton(onClick = { onClickSearch() }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        },
        label = {
            Text(text = labelText)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    )
}