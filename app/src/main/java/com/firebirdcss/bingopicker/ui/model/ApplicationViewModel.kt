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

/**
 * VIEW_MODEL: This is the primary view model class of the application.
 * All interactions with state are done through this view model,
 * also all of the so called business logic of the application are
 * done in here.
 */
class ApplicationViewModel: ViewModel() {
    private var _appUiState = MutableStateFlow(ApplicationUiState())
    val appUiState: StateFlow<ApplicationUiState>
        get() = _appUiState.asStateFlow()

    /**
     * This Function is used to invoke a reset of the application
     * by resetting the state.
     */
    fun resetGame() {
        _appUiState.value = ApplicationUiState()
    }

    /**
     * This Function is used to cause the picking of a new Bingo number.
     * This will get invoked for every pick that takes place.
     */
    fun pickANumber() {
        // Pick randomly from all available numbers
        val pick: Pick = when (val number: Int = _appUiState.value.numbersToPickFrom.random()) {
            in BingoRow.B.value -> Pick(_appUiState.value.nextSequence, BingoRow.B, number)
            in BingoRow.I.value -> Pick(_appUiState.value.nextSequence, BingoRow.I, number)
            in BingoRow.N.value -> Pick(_appUiState.value.nextSequence, BingoRow.N, number)
            in BingoRow.G.value -> Pick(_appUiState.value.nextSequence, BingoRow.G, number)
            else -> Pick(_appUiState.value.nextSequence, BingoRow.O, number)
        }

        // Add the new pick to past picks list
        var pastPicks = mutableListOf<Pick>().apply {
            this.add(pick)
            this.addAll(_appUiState.value.pastPicks)
        }

        // pull a list of numbers to pick from that doesn't contain recent pick
        val toPickFrom = _appUiState.value.numbersToPickFrom.filter { it != pick.value }

        // Update state to reflect current pick
        _appUiState.update { i ->
            i.copy(
                nextSequence = (pick.sequence + 1),
                currentPick = pick,
                pastPicks = pastPicks,
                numbersToPickFrom = toPickFrom,
                isGameOver = toPickFrom.isEmpty(),
                validationResultMessage = "",
            )
        }
    }

    /**
     * This Function handles what happens when the text is being entered for
     * number validation. A value suppression Regex is used to help enforce
     * some sanity on the text that is entered.
     */
    fun enterVerifyNumbersCsvText(text: String) {
        if (text.matches(Regex("((\\s*\\d\\d?\\s*,?)(,\\s*\\d\\d?\\s*,?)*)?"))) { // Value is at least mostly sane...
            // Update State to reflect the newly entered text
            _appUiState.update {
                it.copy(
                    verifyNumbersTextField = text,
                    validationResultMessage = "",
                    isVerifyTextValid = true,
                )
            }
        }
    }

    /**
     * This Function is called to perform a verification of the numbers that were entered
     * so that the user can know if the values entered were actually picked by the app.
     */
    fun doVerifyCalledNumbers() {
        val toVerify = _appUiState.value.verifyNumbersTextField.split(",")
        var notCalled = mutableListOf<String>()

        try { // Because below will fail if a number isn't able to be parsed...
            // Verify if the numbers to verify were called by adding those what weren't
            // to a list of numbers that were notCalled.
            toVerify.forEach { // Will generate an error if data invalid...
                num ->
                    if (_appUiState.value.pastPicks.none { num.toInt() == it.value }) {
                        notCalled.add(num)
                    }
            }

            // Handle if there was or wasn't numbers called
            if (notCalled.isNotEmpty()) { // Some numbers were not called...
                // Update state to reflect numbers not called
                _appUiState.update {
                    it.copy(
                        isVerifyTextValid = true,
                        isNumbersValid = false,
                        verifyNumbersTextField = "",
                        validationResultMessage = "The following were not called:\n$notCalled",
                    )
                }
            } else { // Numbers submitted were called...
                // Update state to reflect that all numbers were called
                _appUiState.update {
                    it.copy(
                        verifyNumbersTextField = "",
                        isVerifyTextValid = true,
                        isNumbersValid = true,
                        validationResultMessage = "All numbers were called!",
                    )
                }
            }
        } catch (e: NumberFormatException) { // Non-Numeric data was entered...
            // Update state to reflect that some data was non-numeric
            _appUiState.update { it.copy(
                isVerifyTextValid = false,
            ) }
        }
    }
}