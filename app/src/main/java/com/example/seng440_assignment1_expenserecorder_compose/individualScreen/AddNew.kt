package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ImageResourceDemo
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ProductType
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ProductViewModel
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.utilities.typeMapping
import com.example.seng440_assignment1_expenserecorder_compose.utilities.vibrateOnLoad

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNew(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val product: ProductViewModel = viewModel()
    val productDetail by product.uiState.collectAsState()
    val mContext = LocalContext.current
    var pNameAndCost =  stringResource(R.string.please_input_product_name_and_cost)
    var succAdd = stringResource(R.string.successfully_added)
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
                            .padding(bottom = 20.dp, top = 150.dp)
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
                    label = { Text(stringResource(id = R.string.item_cost)) },
                    isError = !productDetail.cost.toString().matches(Regex("^[0-9]*$")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                var type = MyExposedDropdownMenuBox()

                Button(
                    onClick = {
                        if (productDetail.name == "" || productDetail.cost == 0) {

                            mToast(mContext,pNameAndCost)
                        } else {
                            generateSound()
                            userViewModel.updateProduct(product = product._uiState.value)
                            mToast(mContext, succAdd)
                            navHostController.navigate(Screen.MainScreen.route)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 200.dp)
                ) {
                    Text(text = stringResource(R.string.add))
                }
            }
            vibrateOnLoad()
        }
    }
}
fun mToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropdownMenuBox(productViewModel: ProductViewModel = viewModel() ) {

    val categories = typeMapping()
    val categoryToProductTypeMap = categories.zip(ProductType.values()).toMap()
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
                            categoryToProductTypeMap[item]?.let { productViewModel.updateType(it) }
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
