package com.chrispassold.domain.models

sealed interface LoginOption {
    data class SocialMedia(val option: SocialMediaOption) : LoginOption
    data class Account(val email: String, val password: String) : LoginOption
}