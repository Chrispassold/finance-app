package com.chrispassold.domain.models

data class User(
    val id: String,
    val externalId: String,
    val fullName: String,
    val email: String,
    val image: String,
)