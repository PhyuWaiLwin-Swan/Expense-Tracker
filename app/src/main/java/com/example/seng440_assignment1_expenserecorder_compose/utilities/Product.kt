package com.example.seng440_assignment1_expenserecorder_compose.utilities

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
        val type: ProductType = this.type
        return ("Product Name:" + name +"\n" +
                "Product Type:" + type.name +"\n" +
                "Cost: " + cost +"\n" +
                "Purchase Date" + date
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
