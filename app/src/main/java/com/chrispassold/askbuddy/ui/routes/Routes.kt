package com.chrispassold.askbuddy.ui.routes

import kotlinx.serialization.Serializable

object Routes {

    object Login {
        @Serializable
        object SignIn

        @Serializable
        object SignUp

        @Serializable
        object ForgetPassword
    }

}