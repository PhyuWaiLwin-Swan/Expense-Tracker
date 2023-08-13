package com.example.seng440_assignment1_expenserecorder_compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Navigation(userViewModel: UserViewModel = viewModel())  {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen") {
        composable(route = Screen.HomeScreen.route) { HomeScreen(navHostController = navController,userViewModel) }
        composable(route = Screen.SetupScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
            type= NavType.StringType
            defaultValue = "Swan"
            nullable = true
        }
        )
        ) { entry -> val name = entry.arguments?.getString("name")
            SetupScreen(name= name?: "swan",navHostController = navController,userViewModel) }
        composable(route = Screen.MainScreen.route) { MainScreen(navHostController = navController,userViewModel) }
        composable(route = Screen.AddNew.route) { HomeScreen(navHostController = navController,userViewModel) }
        /*...*/
    }
}