package com.antoniocostadossantos.git_repos_jetpackcompose.model.commit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CommitDetailsResponse(
    @SerialName("author")
    val author: AuthorDescriptionResponse,
    @SerialName("message")
    val message: String,
): Parcelable