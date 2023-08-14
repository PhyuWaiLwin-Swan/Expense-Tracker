package com.example.seng440_assignment1_expenserecorder_compose

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.ui.theme.Purple40
import com.example.seng440_assignment1_expenserecorder_compose.ui.theme.Purple80


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
        var gender = SimpleRadioButtonComponent()

        TextField(
            value = userDataState.email,
            onValueChange = { newText -> userViewModel.updateEmail(newText) },
            label = { Text(stringResource(R.string.your_email)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userDataState.phone,
            onValueChange = { newText -> userViewModel.updatePhone(newText)},
            label = { Text(stringResource(R.string.your_phone)) },
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
            label = { Text(stringResource(R.string.set_up_budget_dollar)) },
            isError = !userDataState.setupMoney.toString().matches(Regex("^[0-9]*$")),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            userViewModel.updateDate()
            userViewModel.updateName(text)
            userViewModel.updateGender(gender)
            navHostController.navigate(Screen.MainScreen.route)},
            modifier = Modifier.align(Alignment.End)
        ){
            Text(text= stringResource(R.string.setup_user_detail))
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
                    ImageResourceDemo(userDataState.gender)
                    Text(
                        text = userDataState.name,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }


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
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navHostController.navigate(Screen.AddNew.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.add_new_expense))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        navHostController.navigate(Screen.LookupDetail.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.look_up_detail))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

@Composable
fun AddNew(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val product: ProductViewModel = viewModel()
    val productDetail by product.uiState.collectAsState()
    val mContext = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                    ) {
                        ImageResourceDemo(productDetail.type.toString())
                    }
                }
                TextField(
                    value = productDetail.name,
                    onValueChange = { newText ->
                        product.updateName(newText)
                    },
                    label = { Text(stringResource(R.string.product_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                TextField(
                    value = productDetail.cost.toString(),
                    onValueChange = { newText ->
                        val pattern = Regex("^[0-9]*$")
                        if (newText.matches(pattern)) {
                            val newSetupMoney = newText.toIntOrNull() ?: 0
                            product.updateCost(newSetupMoney)
                        }
                    },
                    label = { Text("Item cost:") },
                    isError = !productDetail.cost.toString().matches(Regex("^[0-9]*$")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                var type = MyExposedDropdownMenuBox()

                Button(
                    onClick = {
                        userViewModel.updateProduct(product = product._uiState.value)
                        mToast(mContext, "Successfully Added")
                        navHostController.navigate(Screen.MainScreen.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(R.string.add))
                }
            }
        }
    }
}

@Composable
fun SimpleRadioButtonComponent(): String {
    val radioOptions = listOf("Male", "Female")
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
                        onClick = { onOptionSelected(text) }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun MyExposedDropdownMenuBox(productViewModel: ProductViewModel = viewModel() ) {


    val categories = ProductType::class.java.enumConstants.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(categories[0]) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            productViewModel.updateType(item)
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


private fun mToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

@Composable
fun LookupDetail(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {

    val user by userViewModel.uiState.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
            .background(color = Purple80)
            .clip(RoundedCornerShape(16.dp)),

        ) {
        Column(modifier= Modifier.fillMaxWidth()) {

            Box(modifier = Modifier
                    .fillMaxWidth()
                ) {
                Text(text = "Expense List:", fontSize = 30.sp, modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterStart))
                Button(modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterEnd),onClick = {
                    var string: String = ""
                    for (i in user.productList.toList()){
                        string += i.toString() + "\n"+" "+"\n"
                    }
                    val intent= Intent(Intent.ACTION_SEND).apply {
                    type="text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayListOf("swanpwl01@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Records from expense tracker")
                    putExtra(Intent.EXTRA_TEXT, "This is the text part of the mail")
                }

                    if(intent.resolveActivity(context.packageManager)!=null){
                        startActivity( context,intent,null)
                    }

                }) {
                    Text(text= "Share")
                }
            }
            val user by userViewModel.uiState.collectAsState()
            val products = user.productList.toList()
            var openDialog by remember { mutableStateOf(false) }
            var selectedProduct by remember { mutableStateOf(products[0]) }
            ProductList(products, onProductClick = { friend ->
                //Toast.makeText(this, friend.name, Toast.LENGTH_LONG).show()
                selectedProduct = friend
                openDialog = true
            })

            if (openDialog) {
                AlertDialog(
                    onDismissRequest = { openDialog = false },
                    title = {
                        Text(
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            text = "Expense Detail"
                        )
                    },
                    text = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            ImageResourceDemo(selectedProduct.type.toString())
                            Text(text = stringResource(R.string.product_name) + selectedProduct.name)
                            Text(text = stringResource(R.string.item_cost) + selectedProduct.cost)
                            Text(text = "Item type: " + selectedProduct.type)
                        }
                    },
                    confirmButton = {
                        // Define your custom button using the Button composable

                    },
                    dismissButton = {
                        // You can define a dismiss button here if needed

                    }
                )
            }
        }
    }
}


@Composable
fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn {
        items(products) { product ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .padding(4.dp)
                .background(color = Purple40)
                .clickable {
                    onProductClick(product)
                },) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(all = 2.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    text = product.name
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(all = 2.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    text = "$" + product.cost.toString()
                )
            }
        }
    }
}


