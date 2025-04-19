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

    object Profile {
        @Serializable
        object Profile

        @Serializable
        object BankAccounts

        @Serializable
        object CreditCards

        @Serializable
        object PersonalInformation

        @Serializable
        object Categories

        @Serializable
        object ExitApp
    }

}