package com.example.seng440_assignment1_expenserecorder_compose.utilities

import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.seng440_assignment1_expenserecorder_compose.R
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush

@OptIn(ExperimentalAnimationGraphicsApi::class)
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
    val rainbowColors = listOf(
        Color.Red, Color.Yellow,
        Color.Green, Color.Blue
    )

    val gradientBrush = Brush.linearGradient(rainbowColors)
    val infTran = rememberInfiniteTransition()
    val rotAni = infTran.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing= LinearEasing))
    )
    val image: Painter = painterResource(id = selected[gender]!!)
    Image(painter = image,
        contentDescription = "",
        modifier = Modifier.drawBehind {
        rotate(rotAni.value){
            drawCircle(brush = gradientBrush, style= Stroke(width = 4.dp.toPx()))
        }
    }
        .size(120.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, shape = CircleShape))


}


//fun pickImage() {
//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        // Callback is invoked after the user selects a media item or closes the
//        // photo picker.
//        if (uri != null) {
//            Log.d("PhotoPicker", "Selected URI: $uri")
//        } else {
//            Log.d("PhotoPicker", "No media selected")
//        }
//    }
//
//// Include only one of the following calls to launch(), depending on the types
//// of media that you want to let the user choose from.
//
//// Launch the photo picker and let the user choose images and videos.
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
//
//// Launch the photo picker and let the user choose only images.
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//
//// Launch the photo picker and let the user choose only videos.
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
//
//// Launch the photo picker and let the user choose only images/videos of a
//// specific MIME type, such as GIFs.
//    val mimeType = "image/gif"
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
//}