package com.example.androidexamtest.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Destinations {

    @Serializable
    data object HomeScreen : Destinations()

    @Serializable
    data object DetailScreen : Destinations()
}