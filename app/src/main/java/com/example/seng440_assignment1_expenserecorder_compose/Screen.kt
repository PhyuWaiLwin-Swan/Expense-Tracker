package com.example.seng440_assignment1_expenserecorder_compose

sealed class Screen(val route: String){
    object HomeScreen: Screen("HomeScreen")
    object SetupScreen: Screen("SetupScreen")
    object MainScreen: Screen("MainScreen")
    object AddNew: Screen("AddNew")

    fun withArgs(vararg args: String): String {
        return  buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }
}
