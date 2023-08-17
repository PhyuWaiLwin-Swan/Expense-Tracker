package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ImageResourceDemo
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.utilities.vibrateOnLoad

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val userDataState by userViewModel.uiState.collectAsState()
    userViewModel.updateSetup()

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
                    println(userDataState.gender)
                    ImageResourceDemo(userDataState.gender)
                    Text(
                        text = userDataState.name,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                println(userDataState.name)
                Text(text= stringResource(id = R.string.name) + userDataState.name,
                    fontSize = 35.sp, modifier = Modifier.padding(10.dp))
                Text(
                    text = stringResource(R.string.email) + userDataState.email, fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.phone) + userDataState.phone, fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.set_up_amount) + userDataState.setupMoney, fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.set_up_date) + userDataState.setupDate, fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.current_balance) + userViewModel.getCurrentBalance(), fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(

                    onClick = {

                        generateSound()
                        navHostController.navigate(Screen.AddNew.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.add_new_expense))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        generateSound()
                        navHostController.navigate(Screen.LookupDetail.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.look_up_detail))
                }
                Spacer(modifier = Modifier.height(8.dp))
                vibrateOnLoad()
            }
        }
    }

}