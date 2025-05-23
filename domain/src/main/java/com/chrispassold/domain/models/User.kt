package com.chrispassold.domain.models

data class User(
    val id: String,
    val fullName: String,
    val email: Email,
    val password: Password,
    val image: String? = null,
)