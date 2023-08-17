package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.os.Build
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.utilities.vibrateOnLoad

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController,userViewModel: UserViewModel = viewModel()) {
    var m = stringResource(R.string.please_input_your_name)
    val userDataState by userViewModel.uiState.collectAsState()
    val mContext = LocalContext.current
    var textFieldError by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center,
    modifier= Modifier
        .fillMaxSize()
        .padding(horizontal = 50.dp)
        .verticalScroll(rememberScrollState()))
    {
        Text(text= stringResource(R.string.welcome_to_the_expanse_recorder), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), fontSize = 20.sp)
        TextField(
            value = userDataState.name,
            onValueChange = { newText -> userViewModel.updateName(newText)
                textFieldError = userDataState.name.isEmpty() },
            label = { Text(stringResource(R.string.your_name)) },
            isError = (userDataState.name == ""),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {


            if( userDataState.name == "") {

                mToast(mContext, m)
                } else {


                generateSound()
                navHostController.navigate(Screen.SetupScreen.route)

            }

                         },
            modifier = Modifier.align(Alignment.End)


        ){
            Text(text= stringResource(R.string.to_set_up_screen))

        }
    }

    vibrateOnLoad()



}