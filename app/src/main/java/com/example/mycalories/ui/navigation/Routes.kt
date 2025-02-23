package com.example.mycalories.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object HomeScreen : Routes()

    @Serializable
    object History : Routes()
}