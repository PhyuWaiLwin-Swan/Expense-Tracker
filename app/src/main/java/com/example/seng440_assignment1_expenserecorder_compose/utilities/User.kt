package com.example.seng440_assignment1_expenserecorder_compose.utilities

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



data class User(val name: String, var email:String, var phone:String,
                var setupMoney: Int, var setupDate: String, var gender: String, val productList: MutableList<Product> = mutableListOf() ) {
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

