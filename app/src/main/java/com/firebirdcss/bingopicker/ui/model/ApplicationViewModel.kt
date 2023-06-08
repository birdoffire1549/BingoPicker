package com.firebirdcss.bingopicker.ui.model

import androidx.lifecycle.ViewModel
import com.firebirdcss.bingopicker.data.enums.BingoRow
import com.firebirdcss.bingopicker.data.poko.Pick
import com.firebirdcss.bingopicker.data.state.ApplicationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.NumberFormatException

class ApplicationViewModel: ViewModel() {
    private var _appUiState = MutableStateFlow(ApplicationUiState())
    val appUiState: StateFlow<ApplicationUiState>
        get() = _appUiState.asStateFlow()

    fun resetGame() {
        _appUiState.value = ApplicationUiState()
    }

    fun pickANumber() {
        val pick: Pick = when (val number: Int = _appUiState.value.numbersToPickFrom.random()) {
            in BingoRow.B.value -> Pick(_appUiState.value.nextSequence, BingoRow.B, number)
            in BingoRow.I.value -> Pick(_appUiState.value.nextSequence, BingoRow.I, number)
            in BingoRow.N.value -> Pick(_appUiState.value.nextSequence, BingoRow.N, number)
            in BingoRow.G.value -> Pick(_appUiState.value.nextSequence, BingoRow.G, number)
            else -> Pick(_appUiState.value.nextSequence, BingoRow.O, number)
        }

        var pastPicks = mutableListOf<Pick>().apply {
            this.add(pick)
            this.addAll(_appUiState.value.pastPicks)
        }

        val toPickFrom = _appUiState.value.numbersToPickFrom.filter { it != pick.value }

        _appUiState.update { i ->
            i.copy(
                nextSequence = (pick.sequence + 1),
                currentPick = pick,
                pastPicks = pastPicks,
                numbersToPickFrom = toPickFrom,
                isGameOver = toPickFrom.isEmpty(),
            )
        }
    }

    fun enterVerifyNumbersCsvText(text: String) {
        _appUiState.update {
            it.copy(
                verifyNumbersTextField = text,
                validationResultMessage = "",
                isVerifyTextValid = true,
            )
        }
    }

    fun doVerifyCalledNumbers() {
        val toVerify = _appUiState.value.verifyNumbersTextField.split(",")
        var badNums = mutableListOf<String>()

        try {
            toVerify.forEach { // Will generate an error if data invalid...
                num ->
                    if (_appUiState.value.pastPicks.none { num.toInt() == it.value }) {
                        badNums.add(num)
                    }
            }

            if (badNums.isNotEmpty()) { // Some numbers were not called...
                _appUiState.update {
                    it.copy(
                        isVerifyTextValid = true,
                        isNumbersValid = false,
                        validationResultMessage = "The following were not called:\n${badNums.toString()}"
                    )
                }
            } else { // Numbers submitted were called...
                _appUiState.update {
                    it.copy(
                        verifyNumbersTextField = "",
                        isVerifyTextValid = true,
                        isNumbersValid = true,
                        validationResultMessage = "All numbers were called!"
                    )
                }
            }
        } catch (e: NumberFormatException) {
            _appUiState.update { it.copy(
                isVerifyTextValid = false,
            ) }
        }
    }
}