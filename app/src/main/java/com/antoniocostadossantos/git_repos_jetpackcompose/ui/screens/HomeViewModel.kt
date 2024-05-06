package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.git_repos_jetpackcompose.data.GitHubRestApi
import com.antoniocostadossantos.git_repos_jetpackcompose.model.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class API {
    LOADING,
    ERROR,
    SUCCESS,
    STOPPED,
}

data class HomeUiState(
    val repositories: List<Item> = emptyList(),
    val lang: String = "kotlin",
    val onSearchChange: (String) -> Unit = {},
    val loading: API = API.STOPPED,
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

    fun fetchData() {
        viewModelScope.launch {
            changeStateLoading(API.LOADING)

            _uiState.update { currentState ->
                currentState.copy(
                    repositories = try {
                        changeStateLoading(API.SUCCESS)
                        api.getRepositories(_uiState.value.lang).items
                    } catch (e: Exception) {
                        Log.i("TESTE", e.toString())
                        changeStateLoading(API.ERROR)
                        emptyList()
                    }
                )
            }
        }
    }

    private fun changeStateLoading(actualState: API) {
        _uiState.update { currentState -> currentState.copy(loading = actualState) }
    }

}