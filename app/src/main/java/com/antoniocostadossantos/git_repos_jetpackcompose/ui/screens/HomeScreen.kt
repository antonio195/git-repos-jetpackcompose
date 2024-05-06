package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.RepositoryItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {

    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = uiState.lang,
            onValueChange = {
                uiState.onSearchChange(it)
            },
            trailingIcon = {
                IconButton(onClick = { viewModel.fetchData() }) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                }
            },
            label = {
                Text(text = "Search")
            },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn {
            items(uiState.repositories, key = {
                it.id
            }) {
                RepositoryItem(item = it)
            }
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}