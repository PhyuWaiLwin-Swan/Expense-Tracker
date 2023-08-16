package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.seng440_assignment1_expenserecorder_compose.utilities.ImageResourceDemo
import com.example.seng440_assignment1_expenserecorder_compose.utilities.Product
import com.example.seng440_assignment1_expenserecorder_compose.R
import com.example.seng440_assignment1_expenserecorder_compose.utilities.UserViewModel
import com.example.seng440_assignment1_expenserecorder_compose.ui.theme.Purple40
import com.example.seng440_assignment1_expenserecorder_compose.ui.theme.Purple80

@Composable
fun LookupDetail(navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {

    val user by userViewModel.uiState.collectAsState()
    val context = LocalContext.current
    var string = ""
    for (i in user.productList.toList()){
        string += i.toString() + "\n"+" "+"\n"
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(color = Purple80)
            .clip(RoundedCornerShape(35.dp)),

        ) {
        Column(modifier= Modifier.padding(20.dp, top = 40.dp, end = 20.dp)) {

            Box(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Expense List:", fontSize = 30.sp, modifier = Modifier
                    .padding(top = 8.dp)
                    .align(
                        Alignment.CenterStart
                    ))
                Button(modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterEnd),onClick = {
                    generateSound()
                    val intent= Intent(Intent.ACTION_SEND).apply {
                        type="text/plain"
                        putExtra(Intent.EXTRA_EMAIL, arrayListOf("aungminoo83@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "Records from expense tracker")
                        putExtra(Intent.EXTRA_TEXT, string)
                    }

                    if(intent.resolveActivity(context.packageManager)!=null){
                        ContextCompat.startActivity( context,intent,null)
                    }

                }) {
                    Text(text= "Share")
                }
            }
            val user by userViewModel.uiState.collectAsState()
            val products = user.productList.toList()
            var openDialog by remember { mutableStateOf(false) }

            if(! products.isEmpty()) {
                var selectedProduct by remember { mutableStateOf(products[0]) }
                ProductList(products, onProductClick = { friend ->
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

            else {
                Text("No saved expense", fontSize = 20.sp)
            }
        }
    }
}
@Composable
fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    LazyColumn {
        items(products) { product ->


//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(2.dp)
//            ) {
//                ImageResourceDemo(gender = product.type.toString())
//                Text(
//                    modifier = Modifier
//                        .align(Alignment.CenterStart)
//                        .padding(all = 2.dp),
//                    style = MaterialTheme.typography.bodyLarge,
//                    fontSize = 25.sp,
//                    text = product.name
//                )
//                Text(
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .padding(all = 2.dp),
//                    style = MaterialTheme.typography.bodyLarge,
//                    fontSize = 25.sp,
//                    text = "$" + product.cost.toString()
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                ItemButton(
//                    expanded = expanded,
//                    onClick = { /*TODO*/ }
//                )
//            }


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


