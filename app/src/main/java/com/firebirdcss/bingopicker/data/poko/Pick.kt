package com.firebirdcss.bingopicker.data.poko

import com.firebirdcss.bingopicker.data.blankBallImages
import com.firebirdcss.bingopicker.data.enums.BingoRow

/**
 * This data class represents a picked bingo ball,
 * where the pick has a sequence in which it was picked
 * and the bingo title row to which it belongs as well as
 * the value of the ball and a reference to the PickBall
 * which carries the Image and Text Shadow information.
 */
data class Pick(
    val sequence: Int = 1,
    val row: BingoRow = BingoRow.B,
    val value: Int = 4,
    val ball: PickBall = blankBallImages.random()
)