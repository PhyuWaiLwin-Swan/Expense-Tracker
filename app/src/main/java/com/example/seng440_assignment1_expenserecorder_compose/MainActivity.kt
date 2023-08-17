package com.example.seng440_assignment1_expenserecorder_compose

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.HomeScreen
import com.example.seng440_assignment1_expenserecorder_compose.individualScreen.Screen
import com.example.seng440_assignment1_expenserecorder_compose.ui.theme.Purple40
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel

class MainActivity : ComponentActivity() {
    val userModel: UserViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter",
        "StateFlowValueCalledInComposition"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = getString(R.string.expense_tracker),
                                color = Purple40 // Set the text color here
                            )
                        } // Set the TopAppBar background color here
                    )
                }
            ) {
                Navigation(userModel)

                
            }
        }
    }

}
