package com.chrispassold.domain.models

@JvmInline
value class Password(val value: String) {
    init {
        require(isValidPassword(value)) { "Invalid password format: must be at least 8 characters long" }
    }
}

private fun isValidPassword(password: String): Boolean {
    return password.length >= 8
}