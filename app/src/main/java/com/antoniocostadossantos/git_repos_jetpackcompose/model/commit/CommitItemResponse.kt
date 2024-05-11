package com.antoniocostadossantos.git_repos_jetpackcompose.model.commit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CommitItemResponse(
    @SerialName("commit")
    val commit: CommitDetailsResponse,
    @SerialName("html_url")
    val htmlUrl: String,
) : Parcelable