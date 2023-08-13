package com.example.seng440_assignment1_expenserecorder_compose

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
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


class Product(
    val name: String,
    val type: ProductType,
    val cost: Int,
    val date: String
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

data class User(val name: String, var email:String, var phone:String, var setupMoney: Int, var setupDate: String) {
    override fun toString() : String {
        return ("User name:" + name +"\n" +
                "User email" + email +"\n" +
                "Phone number: " + phone
                )
    }
}

class UserViewModel: ViewModel() {
    val formatter = SimpleDateFormat("d MMMM HH:mm:ss")
    val today = Calendar.getInstance()
    private val _uiState = MutableStateFlow(User("User","123@email.com", "123456789",200, formatter.format(today)))
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

}
@Composable
fun ImageResourceDemo() {
    val image: Painter = painterResource(id = R.drawable.img)
    Image(painter = image,contentDescription = "",modifier = Modifier
        .size(120.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, shape = CircleShape))
}

