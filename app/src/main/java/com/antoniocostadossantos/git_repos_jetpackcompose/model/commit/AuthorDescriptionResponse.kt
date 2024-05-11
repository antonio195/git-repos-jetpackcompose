package com.antoniocostadossantos.git_repos_jetpackcompose.model.commit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class AuthorDescriptionResponse(
    @SerialName("date")
    val date: String,
    val email: String,
    @SerialName("name")
    val name: String
): Parcelable