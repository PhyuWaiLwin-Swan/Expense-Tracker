package com.example.seng440_assignment1_expenserecorder_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupScreen( name: String,navHostController: NavHostController,userViewModel: UserViewModel = viewModel()) {

    val userDataState by userViewModel.uiState.collectAsState()
    var text by remember{
        mutableStateOf("")
    }
    Box(contentAlignment = Alignment.TopStart,
        modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)) {

        Text(text="Hello $name")
    }
    Column(verticalArrangement = Arrangement.Center,
        modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)) {
        TextField(
            value = userDataState.email,
            onValueChange = { newText -> userViewModel.updateEmail(newText) },
            label = { Text("Your email:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userDataState.phone,
            onValueChange = { newText -> userViewModel.updatePhone(newText)},
            label = { Text("Your phone:") },
            isError = !userDataState.phone.toString().matches(Regex("^[0-9]*$")),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userDataState.setupMoney.toString(),
            onValueChange = { newText ->
                val pattern = Regex("^[0-9]*$")
                if(newText.matches(pattern)){
                    val newSetupMoney = newText.toIntOrNull() ?: 0
                    userViewModel.updateSetupMoney(newSetupMoney)}},
            label = { Text("Set up budget (dollar):") },
            isError = !userDataState.setupMoney.toString().matches(Regex("^[0-9]*$")),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            userDataState.setupDate
            navHostController.navigate(Screen.MainScreen.route)},
            modifier = Modifier.align(Alignment.End)
        ){
            Text(text= "Setup User Detail")
        }
    }
}

@Composable
fun MainScreen(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val userDataState by userViewModel.uiState.collectAsState()

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Text(text = "Hello ${userDataState.name}")

//        Text(text = "Set up amount : ${userDataState.setupMoney}")
//        Text(text = "Current amount : ${userDataState.setupMoney}")
    }
}