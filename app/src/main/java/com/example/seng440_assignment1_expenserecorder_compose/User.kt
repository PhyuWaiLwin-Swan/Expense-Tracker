package com.example.seng440_assignment1_expenserecorder_compose

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class Product(
    var name: String,
    var type: ProductType,
    var cost: Int,
    var date: String
) {
    override fun toString() : String {
        val type:ProductType = this.type
        return ("Product Name:" + name +"\n" +
                "Product Type:" + type.name +"\n" +
                "Cost: " + cost +"\n" +
                "Purchase Date" + date
                )
    }
}

data class User(val name: String, var email:String, var phone:String,
                var setupMoney: Int, var setupDate: String, var gender: String, val productList: MutableList<Product> = mutableListOf() ) {
    override fun toString() : String {
        return ("User name:" + name +"\n" +
                "User email" + email +"\n" +
                "Phone number: " + phone
                )
    }
}

class ProductViewModel: ViewModel(){
    val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
    val today = Calendar.getInstance()
    val _uiState = MutableStateFlow(Product("", ProductType.Food, 0, formatter.format(today)))
    val uiState: StateFlow<Product> = _uiState.asStateFlow()
    fun updateName(newname:String) {
        _uiState.update { currentState -> currentState.copy(name = newname) }
    }
    fun updateCost(newCost:Int) {
        _uiState.update { currentState -> currentState.copy(cost = newCost) }
    }
    fun updateType(newType:String) {
        _uiState.update { currentState -> currentState.copy(type = ProductType.valueOf(newType)) }
    }
    fun updateDate() {
        val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
        val today = Calendar.getInstance()
        _uiState.update { currentState -> currentState.copy(date =formatter.format(today)) }
    }
}

class UserViewModel: ViewModel() {
    val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
    val today = Calendar.getInstance()
    private val _uiState = MutableStateFlow(User("User","123@email.com", "123456789",200, formatter.format(today),"Male"))
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    fun updateName(newname:String) {
        _uiState.update { currentState -> currentState.copy(name = newname) }
    }
    fun updateEmail(newEmail:String) {
        _uiState.update { currentState -> currentState.copy(email = newEmail) }
    }
    fun updatePhone(newPhone:String) {
        _uiState.update { currentState -> currentState.copy(phone = newPhone) }
    }
    fun updateDate() {
        val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
        val today = Calendar.getInstance()
        _uiState.update { currentState -> currentState.copy(setupDate =formatter.format(today)) }
    }
    fun updateSetupMoney(money:Int) {
        _uiState.update { currentState -> currentState.copy(setupMoney = money) }
    }

    fun updateGender(gender:String) {
        _uiState.update { currentState -> currentState.copy(gender = gender) }
    }

    fun updateProduct( product: Product) {
        _uiState.update { currentState ->
            val products = currentState.productList + product
            currentState.copy(productList = products as MutableList<Product>)
        }
    }
}

@Composable
fun ImageResourceDemo(gender: String) {

    var selected = mapOf(
          "Male" to R.drawable.img ,
         "Female" to R.drawable.img_1,
        "Housing" to R.drawable.housing,
        "Food" to R.drawable.food,
        "Transport" to R.drawable.transport,
        "Utilities" to R.drawable.utilities,
        "HealthCare" to R.drawable.healthcare,
        "Insurance" to R.drawable.insurance,
        "Education" to R.drawable.education
    )
    val image: Painter = painterResource(id = selected[gender]!!)
    Image(painter = image,contentDescription = "",modifier = Modifier
        .size(120.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, shape = CircleShape))
}

