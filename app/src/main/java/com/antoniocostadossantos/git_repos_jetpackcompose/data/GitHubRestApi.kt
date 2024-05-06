package com.antoniocostadossantos.git_repos_jetpackcompose.data

import com.antoniocostadossantos.git_repos_jetpackcompose.model.RepositoryData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GitHubRestApi(
    private val client: HttpClient
) {

    suspend fun getRepositories(lang: String): RepositoryData {
        return client.get(baseUrl(lang)).body()
    }

    private fun baseUrl(lang: String): String {
        return "https://api.github.com/search/repositories?q=language:${lang}&sort=stars&page=&per_page=20"
    }
}