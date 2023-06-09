package com.firebirdcss.bingopicker.data.poko

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.firebirdcss.bingopicker.R

/**
 * This data class is used to represent a pick ball
 * or perhaps just a Blank Bingo Ball where the ball has an
 * image that is what it looks like and a shadow color that
 * is defined for the text placed on the image to use.
 */
data class PickBall(
    @DrawableRes val ballImage: Int = R.drawable.greenblankballnobg,
    val shadowColor: Color = Color.Green
)