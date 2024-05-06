package com.antoniocostadossantos.git_repos_jetpackcompose.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryData(
    @SerialName("items")
    val items: List<Item>,
)