package com.example.seng440_assignment1_expenserecorder_compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.util.Date


class Product(val name: String,
           val type: ProductType,
           val cost: Int,
           val date: Date
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

data class User(val name: String, var email:String, var phone:String, var setupMoney: Int, val setupDate: Date) {
    override fun toString() : String {
        return ("User name:" + name +"\n" +
                "User email" + email +"\n" +
                "Phone number: " + phone
                )
    }
}

class UserViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User("User","123@email.com", "123456789",200, Date()))
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
    fun updateDate(newPhone:String) {
        _uiState.update { currentState -> currentState.copy(setupDate = Date()) }
    }
    fun updateSetupMoney(money:Int) {
        _uiState.update { currentState -> currentState.copy(setupMoney = money) }
    }
}