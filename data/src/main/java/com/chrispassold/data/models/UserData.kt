package com.chrispassold.data.models

data class UserData(
    val id: String,
    val fullName: String,
    val email: String,
    val password: String,
    val image: String?,
)