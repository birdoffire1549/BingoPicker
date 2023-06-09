package com.firebirdcss.bingopicker.data

import androidx.compose.ui.graphics.Color
import com.firebirdcss.bingopicker.R
import com.firebirdcss.bingopicker.data.poko.PickBall

/**
 * Repository of Blank Bingo Ball Images and the shadow
 * color that should be used for the text placed on the
 * image, as a data unit known as a PickBall.
 */
val blankBallImages: List<PickBall> = listOf(
    PickBall(R.drawable.greenblankballnobg, Color.Green),
    PickBall(R.drawable.blueblankballnobg, Color.Blue),
    PickBall(R.drawable.redblankballnobg, Color.Red),
    PickBall(R.drawable.purpleblankballnobg, Color.Magenta),
    PickBall(R.drawable.yellowblankballnobg, Color.Yellow),
)