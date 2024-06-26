package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.antoniocostadossantos.git_repos_jetpackcompose.mock.sampleItemRepository
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.RepositoryItem
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.SearchBar
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.details.RepositoryItemDetails
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class HomeScreen(
    private val modifier: Modifier = Modifier
) : Screen {

    @Composable
    override fun Content() {

        val viewModel = koinViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                text = uiState.lang,
                onSearchChange = { uiState.onSearchChange(it) },
                onClickSearch = {
                    scope.launch {
                        viewModel.fetchData()
                    }
                }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(
                    items = uiState.repositories,
                    key = { it.id }
                ) { item ->
                    RepositoryItem(
                        item = item,
                        onClick = {
                            navigator.push(RepositoryItemDetails(
                                it,
                                modifier = modifier
                            ))
                        }
                    )
                }
            }
            if (uiState.showProgress) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}