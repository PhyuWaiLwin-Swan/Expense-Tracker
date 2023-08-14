package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController,userViewModel: UserViewModel = viewModel()) {
    var text by remember{
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.Center,
    modifier= Modifier
        .fillMaxSize()
        .padding(horizontal = 50.dp)) {
        Text(text= stringResource(R.string.welcome_to_the_expanse_recorder), modifier = Modifier.fillMaxWidth().padding(10.dp), fontSize = 20.sp)
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text(stringResource(R.string.your_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navHostController.navigate(Screen.SetupScreen.withArgs(text))},
            modifier = Modifier.align(Alignment.End)
        ){
            Text(text= stringResource(R.string.to_set_up_screen))
        }
    }


}