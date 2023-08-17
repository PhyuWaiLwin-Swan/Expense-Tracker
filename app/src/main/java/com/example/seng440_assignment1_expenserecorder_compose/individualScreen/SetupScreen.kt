package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.utilities.vibrateOnLoad


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupScreen(navHostController: NavHostController,userViewModel: UserViewModel = viewModel()) {

    val userDataState by userViewModel.uiState.collectAsState()

    var textFieldError by remember { mutableStateOf(true) }
    val mContext = LocalContext.current
    var emailPhone = stringResource(R.string.please_input_your_email_and_phone)
    Box(contentAlignment = Alignment.TopStart,
        modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)) {

        Text(text="Hello ${userDataState.name}")
    }
    Column(verticalArrangement = Arrangement.Center,
        modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(text= stringResource(id = R.string.name) + userDataState.name, fontSize = 35.sp, modifier = Modifier.padding(10.dp, top = 100.dp))
        var gender = SimpleRadioButtonComponent()


        TextField(
            value = userDataState.email,
            onValueChange = { newText -> userViewModel.updateEmail(newText)
                            },
            label = { Text(stringResource(R.string.your_email)) },
            isError = !userDataState.email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$")),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userDataState.phone,
            onValueChange = { newText -> userViewModel.updatePhone(newText)},
            label = { Text(stringResource(R.string.your_phone)) },
            isError = !userDataState.phone.matches(Regex("^[0-9]+$")),
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
            label = { Text(stringResource(R.string.set_up_budget_dollar)) },
            isError = !userDataState.setupMoney.toString().matches(Regex("^[0-9]*$")),
            modifier = Modifier.fillMaxWidth()
        )
        var check = (!userDataState.setupMoney.toString().matches(Regex("^[0-9]*$")) ||
                !userDataState.phone.toString().matches(Regex("^[0-9]*$")) ||
                !userDataState.email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$")))


        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if(userDataState.email !="" && userDataState.phone !="" && ! check){
            generateSound()
            userViewModel.updateDate()
            userViewModel.updateGender(gender)
            navHostController.navigate(Screen.MainScreen.route)
            } else {

                mToast(mContext, emailPhone)
            }},
            modifier = Modifier.align(Alignment.End).padding(bottom = 200.dp)

        ){
            Text(text= stringResource(R.string.setup_user_detail))
        }
    }
    vibrateOnLoad()
}




@Composable
fun SimpleRadioButtonComponent(): String {
    val radioOptions = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onOptionSelected(text) } // Use clickable instead of selectable
                        .padding(horizontal = 8.dp)
                ) {
                    RadioButton(

                        selected = (text == selectedOption),
                        onClick = { generateSound()
                            onOptionSelected(text) }
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add a space between RadioButton and Text
                    Text(
                        text = text,
                        modifier = Modifier.align(Alignment.CenterVertically) // Align Text to the center vertically
                    )
                }
            }
        }
    }

    return selectedOption
}


