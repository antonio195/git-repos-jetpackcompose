package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.commits

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.CommitItem
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.components.RepositoryItem
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.details.RepositoryItemDetails
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class CommitsScreen(
    val owner: String,
    val repo: String,
) : Screen {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<CommitsViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()

        scope.launch {
            viewModel.fetchData()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(
                    items = uiState.commits,
                    key = { it.commit.author }
                ) { item ->
                    CommitItem(commitItemResponse = item)
                }
            }
        }
    }
}