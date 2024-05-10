package com.antoniocostadossantos.git_repos_jetpackcompose.mock

import com.antoniocostadossantos.git_repos_jetpackcompose.model.Item
import com.antoniocostadossantos.git_repos_jetpackcompose.model.Owner

val sampleItemRepository = Item(
    description = "A memory leak detection library for Android.",
    forksCount = 3952,
    fullName = "square/leakcanary",
    homepage = "https://square.github.io/leakcanary", "",
    id = 34824499,
    language = "Kotlin",
    name = "leakcanary",
    owner = Owner(
        avatarUrl = "https://avatars.githubusercontent.com/u/82592?v=4",
        htmlUrl = "https://github.com/square",
        id = 82592,
        login = "square"
    ),
    stargazersCount = 29145
)