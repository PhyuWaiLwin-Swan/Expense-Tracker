package com.example.seng440_assignment1_expenserecorder_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.AddNew
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.HomeScreen
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.LookupDetail
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.MainScreen
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.Screen
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.SetupScreen
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(userViewModel: UserViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable(route = Screen.HomeScreen.route) { HomeScreen(navHostController = navController,userViewModel) }
        composable(route = Screen.SetupScreen.route ){ SetupScreen(navHostController = navController,userViewModel) }
        composable(route = Screen.MainScreen.route) { MainScreen(navHostController = navController,userViewModel) }
        composable(route = Screen.AddNew.route) { AddNew(navHostController = navController,userViewModel) }
        composable(route = Screen.LookupDetail.route) { LookupDetail(navHostController = navController,userViewModel) }
    }
}