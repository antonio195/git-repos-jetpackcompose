package com.antoniocostadossantos.git_repos_jetpackcompose.model.repo


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Item(
    @SerialName("description")
    val description: String,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("open_issues")
    val openIssues: Int,
    @SerialName("watchers")
    val watchers: Int,
    @SerialName("score")
    val score: Double,
): Parcelable