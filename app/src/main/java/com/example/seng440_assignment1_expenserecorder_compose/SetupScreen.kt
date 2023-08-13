package com.example.seng440_assignment1_expenserecorder_compose

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
            userViewModel.updateDate()
            userViewModel.updateName(text)
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

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 50.dp, horizontal = 50.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ImageResourceDemo()
                    Text(
                        text = userDataState.name,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }


                Text(
                    text = "Email: ${userDataState.email}", fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Phone: ${userDataState.phone}", fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Set up amount: $ ${userDataState.setupMoney}", fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Set up date: ${userDataState.setupDate}", fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navHostController.navigate(Screen.HomeScreen.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add new expense")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        navHostController.navigate(Screen.HomeScreen.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Look up Detail")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

@Composable
fun AddNew(navHostController: NavHostController,userViewModel: UserViewModel= viewModel()) {
    val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
    val today = Calendar.getInstance()

    val _uiState = remember { mutableStateOf(Product("", ProductType.Food, 0, formatter.format(today))) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = _uiState.value.name,
            onValueChange = { newText -> _uiState.value = _uiState.value.copy(name = newText) },
            label = { Text("Product Name:") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = _uiState.value.cost.toString(),
            onValueChange = { newText -> _uiState.value = _uiState.value.copy(cost = newText.toInt()) },
            label = { Text("Item name:") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}




