package com.example.androidexamtest.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Destinations {

    @Serializable
    data object HomeScreen : Destinations()

    @Serializable
    data object EJOManagementScreen : Destinations()

    @Serializable
    data object ESRManagementScreen : Destinations()

    @Serializable
    data object FRISManagementScreen : Destinations()

    @Serializable
    data object EJOESRApprovalScreen : Destinations()
}