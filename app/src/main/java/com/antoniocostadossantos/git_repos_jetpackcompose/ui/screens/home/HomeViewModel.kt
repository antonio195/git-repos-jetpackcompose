package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.git_repos_jetpackcompose.data.GitHubRestApi
import com.antoniocostadossantos.git_repos_jetpackcompose.model.repo.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val repositories: List<Item> = emptyList(),
    val lang: String = "kotlin",
    val onSearchChange: (String) -> Unit = {},
    val showProgress: Boolean = false
)

class HomeViewModel(
    private val api: GitHubRestApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    onSearchChange = { newText ->
                        _uiState.update { currentState ->
                            currentState.copy(
                                lang = newText
                            )
                        }
                    }
                )
            }
        }
    }

    suspend fun fetchData() {
        _uiState.update { currentState ->
            currentState.copy(showProgress = true)
        }

        val lang = _uiState.value.lang

        val repositories = api.getRepositories(
            lang = lang,
            page = 1,
            perPage = 20
        ).items
        _uiState.update { currentState ->
            currentState.copy(
                repositories = repositories,
                showProgress = false
            )
        }
    }

}