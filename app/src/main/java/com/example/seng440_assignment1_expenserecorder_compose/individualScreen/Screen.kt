package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

sealed class Screen(val route: String){
    object HomeScreen: Screen("HomeScreen")
    object SetupScreen: Screen("SetupScreen")
    object MainScreen: Screen("MainScreen")
    object AddNew: Screen("AddNew")

    object LookupDetail: Screen("LookupDetail")

}
