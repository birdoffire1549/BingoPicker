package com.firebirdcss.bingopicker.data.state

import com.firebirdcss.bingopicker.data.poko.Pick

data class ApplicationUiState(
    val nextSequence: Int = 1,
    val currentPick: Pick? = null,
    val numbersToPickFrom: List<Int> = (1..75).toList().shuffled(),
    val pastPicks: List<Pick> = listOf(),
    val isGameOver: Boolean = false,

    val verifyNumbersTextField: String = "",
    val isVerifyTextValid: Boolean = true,

    val validationResultMessage: String = "",
    val isNumbersValid: Boolean = true,
)
