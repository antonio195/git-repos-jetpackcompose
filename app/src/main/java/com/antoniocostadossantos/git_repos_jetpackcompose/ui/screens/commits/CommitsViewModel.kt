package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.commits

import androidx.lifecycle.ViewModel
import com.antoniocostadossantos.git_repos_jetpackcompose.data.GitHubRestApi
import com.antoniocostadossantos.git_repos_jetpackcompose.model.commit.CommitItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CommitsUiState(
    val commits: List<CommitItemResponse> = emptyList(),
    val loading: Boolean = false
)

class CommitsViewModel(
    private val api: GitHubRestApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(CommitsUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun fetchData() {
        _uiState.update { currentState ->
            currentState.copy(loading = true)
        }

        val commits = api.getCommits(
            "Jetbrains",
            "kotlin"
        )
        _uiState.update { currentState ->
            currentState.copy(
                commits = commits.toList(),
                loading = false
            )
        }
    }

}