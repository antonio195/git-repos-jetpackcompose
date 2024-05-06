package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.RepositoryItem
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.SearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {

    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var showProgress by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchBar(
            text = uiState.lang,
            onSearchChange = { uiState.onSearchChange(it) },
            onClickSearch = { viewModel.fetchData() }
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = uiState.repositories,
                key = { it.id }
            ) {
                RepositoryItem(item = it)
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .heightIn(min = 1.dp)
                .fillMaxWidth()
        )
        when (uiState.loading) {
            API.LOADING -> {
                showProgress = true
            }

            else -> {
                showProgress = false
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}