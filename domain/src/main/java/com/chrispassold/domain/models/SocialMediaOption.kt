package com.chrispassold.domain.models

sealed interface SocialMediaOption {
    object Google : SocialMediaOption
    object Apple : SocialMediaOption
    object Facebook : SocialMediaOption
}