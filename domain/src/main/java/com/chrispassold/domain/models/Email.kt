package com.chrispassold.domain.models

@JvmInline
value class Email(val value: String) {

    init {
        require(
            android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches(),
        ) { "Invalid email format: $value" }
    }

    override fun toString(): String {
        return value
    }
}