package com.firebirdcss.bingopicker.data.poko

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.firebirdcss.bingopicker.R

data class PickBall(
    @DrawableRes val ballImage: Int = R.drawable.greenblankballnobg,
    val shadowColor: Color = Color.Green
)
