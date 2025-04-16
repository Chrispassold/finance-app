package com.chrispassold.askbuddy.ui.routes

import kotlinx.serialization.Serializable

object Routes {

    object Login {
        @Serializable
        object Start

        @Serializable
        object CreateAccount

        @Serializable
        object ForgetPassword
    }

}