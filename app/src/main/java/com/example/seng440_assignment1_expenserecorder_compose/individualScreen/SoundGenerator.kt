package com.example.seng440_assignment1_expenserecorder_compose.individualScreen

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.runtime.Composable

fun generateSound() {
    val time = 200
    val toner = ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
    toner.startTone(ToneGenerator.TONE_CDMA_ABBR_REORDER, time)
}