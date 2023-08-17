package com.example.seng440_assignment1_expenserecorder_compose.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.seng440_assignment1_expenserecorder_compose.R

enum class ProductType {
    Food, Clothing, Transport, Housing, Utilities, HealthCare, Insurance, Education
}
@Composable
fun typeMapping() : List<String> {
    val categories = ProductType::class.java.enumConstants.map { productType ->
        val stringResId = when (productType) {
            ProductType.Food -> R.string.food
            ProductType.Education -> R.string.education
            ProductType.Insurance -> R.string.insurance
            ProductType.Utilities -> R.string.utilities
            ProductType.Housing -> R.string.housing
            ProductType.HealthCare -> R.string.healthcare
            ProductType.Clothing -> R.string.clothing
            ProductType.Transport -> R.string.transport
        }
        stringResource(id = stringResId)
    }
    return categories
}


fun getMapping(productType: ProductType) : Int {
    return when (productType) {
        ProductType.Food -> R.string.food
        ProductType.Education -> R.string.education
        ProductType.Insurance -> R.string.insurance
        ProductType.Utilities -> R.string.utilities
        ProductType.Housing -> R.string.housing
        ProductType.HealthCare -> R.string.healthcare
        ProductType.Clothing -> R.string.clothing
        ProductType.Transport -> R.string.transport
    }

}


