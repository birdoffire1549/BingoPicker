package com.firebirdcss.bingopicker.data.poko

import com.firebirdcss.bingopicker.data.blankBallImages
import com.firebirdcss.bingopicker.data.enums.BingoRow

data class Pick(
    val sequence: Int = 1,
    val row: BingoRow = BingoRow.B,
    val value: Int = 4,
    val ball: PickBall = blankBallImages.random()
)
