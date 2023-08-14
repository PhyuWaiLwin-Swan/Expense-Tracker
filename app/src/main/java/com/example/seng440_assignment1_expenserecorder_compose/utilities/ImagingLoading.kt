package com.example.seng440_assignment1_expenserecorder_compose.utilities

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
import com.example.seng440_assignment1_expenserecorder_compose.R

@Composable
fun ImageResourceDemo(gender: String) {

    val selected = mapOf(
        "Male" to R.drawable.img,
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

