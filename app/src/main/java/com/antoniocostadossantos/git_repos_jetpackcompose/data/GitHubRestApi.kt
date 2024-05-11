package com.antoniocostadossantos.git_repos_jetpackcompose.data

import com.antoniocostadossantos.git_repos_jetpackcompose.model.commit.CommitItemResponse
import com.antoniocostadossantos.git_repos_jetpackcompose.model.repo.RepositoryData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class GitHubRestApi(
    private val client: HttpClient
) {

    suspend fun getRepositories(
        lang: String,
        page: Int,
        perPage: Int
    ): RepositoryData {
        return client.get(
            baseUrl() + searchRepositories(
                lang = lang,
                page = page,
                perPage = perPage
            )
        ).body()
    }

    suspend fun getCommits(owner: String, repo: String): Array<CommitItemResponse> {
        return client.get(baseUrl() + fetchCommits(owner = owner, repo = repo)).body()
    }

    private fun baseUrl(): String {
        return "https://api.github.com/"
    }

    private fun fetchCommits(
        owner: String,
        repo: String,
    ): String {
        return "repos/{$owner}/{$repo}/commits"
    }

    private fun searchRepositories(
        lang: String,
        page: Int,
        perPage: Int,
    ): String {
        return "search/repositories?q=language:${lang}&sort=stars&page=${page}&per_page=${perPage}"
    }
}