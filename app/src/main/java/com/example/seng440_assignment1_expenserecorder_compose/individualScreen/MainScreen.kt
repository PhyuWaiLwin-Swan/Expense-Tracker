package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.content.res.Configuration
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ImageResourceDemo
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.utilities.vibrateOnLoad

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val userDataState by userViewModel.uiState.collectAsState()
    userViewModel.updateSetup()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp, horizontal = 50.dp)
    ) {
        val configuration = LocalConfiguration.current

        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp, bottom = 20.dp)
                ) {
                    ImageResourceDemo(userDataState.gender)

                }
                Text(
                    text = stringResource(R.string.name) + userDataState.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                // Rest of the text and buttons (bottom content)
                Text(
                    text = stringResource(R.string.email) + userDataState.email,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.phone) + userDataState.phone,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.set_up_amount) + userDataState.setupMoney,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.set_up_date) + userDataState.setupDate,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.current_balance) + userViewModel.getCurrentBalance(),
                    fontSize = 20.sp,
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
        } else {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 10.dp)
                    ) {
                        ImageResourceDemo(userDataState.gender)
                    }
                    Text(
                        text = stringResource(R.string.name) + userDataState.name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )
                    Row() {
                        Button(
                            onClick = {
                                generateSound()
                                navHostController.navigate(Screen.AddNew.route)
                            },
                        ) {
                            Text(text = stringResource(R.string.add_new_expense))
                        }
                        Button(
                            onClick = {
                                generateSound()
                                navHostController.navigate(Screen.LookupDetail.route)
                            },
                        ) {
                            Text(text = stringResource(R.string.look_up_detail))
                        }
                    }
                }

                // Rest of the text and buttons (right content)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.email) + userDataState.email,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.phone) + userDataState.phone,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.set_up_amount) + userDataState.setupMoney,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.set_up_date) + userDataState.setupDate,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.current_balance) + userViewModel.getCurrentBalance(),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    vibrateOnLoad()
                }
            }
        }
    }
}
