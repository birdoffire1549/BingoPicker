package com.firebirdcss.bingopicker.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebirdcss.bingopicker.R
import com.firebirdcss.bingopicker.ui.component.PastPick
import com.firebirdcss.bingopicker.ui.model.ApplicationViewModel

/**
 * COMPOSABLE: This composable is the primary screen of the application, all other
 * things displayed in this application are children of this composable.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview (showBackground = true)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
) {
    val vModel: ApplicationViewModel = viewModel()
    val uiState = vModel.appUiState.collectAsState()

    Column( // <------------------------------------------------------------------- Master Container
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize(),
    ) {
        Column( // <----------------------------------------------------------------- Header Section
            modifier = modifier,
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Image( // <------------------------------------------------- Bingo Balls Title Image
                    painter = painterResource(id = R.drawable.bingowordballsnobgsm),
                    contentDescription = stringResource(R.string.descr_bingo_balls_title_image)
                )
                Row( // <------------------------------------ Container for Number Picker title text
                    modifier = modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text( // <--------------------------------------------- Number Picker Title Text
                        text = stringResource(R.string.txt_number_picker_title),
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Green,
                                blurRadius = 10f,
                            )
                        )
                    )
                }
            }
        }
        Column( // <-------------------------------------- Container for everything below the header
            modifier = modifier
                .fillMaxSize(),
        ) {
            Spacer(modifier = modifier.height(30.dp)) // <------------------------ Post title spacer
            Row( // <-------------------------------------------------------- Current Pick container
                modifier = modifier
                    .padding(20.dp)

                    .height(200.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box( // <------------------ Container for Bingo Cards Image Button and Picked Number
                    modifier = modifier
                        .clickable { // Only clickable if game is NOT over.
                           if (!uiState.value.isGameOver) { // Game Not Over...
                               vModel.pickANumber()
                           }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image( // <--------------------------------- Bingo Cards Image and Picker Button
                        modifier = modifier,
                        painter = painterResource(id = R.drawable.greencardsredmarkersnobg),
                        contentDescription = stringResource(R.string.descr_bingo_cards_image),
                        alpha = .3f
                    )
                    if (uiState.value.currentPick != null) { // A current pick exists...
                        Text( // <---------------------------------------- Current Pick display Text
                            text = "${uiState.value.currentPick?.row?.name}-${uiState.value.currentPick?.value}",
                            modifier = modifier
                                .testTag("CurrentPick_Text"),
                            fontSize = 100.sp,
                        )
                    } else { // No pick currently...s
                        Text(
                            text = stringResource(R.string.txt_click_to_pick),
                            fontSize = 50.sp,
                        )
                    }
                }
            }
            if (uiState.value.isGameOver) { // Game is over...
                Row( // <-------------------------------------------------- Game Over Text Container
                    modifier = modifier
                        .padding(start = 20.dp, bottom = 10.dp, end = 20.dp)
                        .background(color = Color.Red)
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text( // <------------------------------------------------------- Game Over Text
                        modifier = modifier
                            .testTag("GameOver_Text"),
                        text = stringResource(R.string.txt_game_is_over),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Column( // <------------------------ Container for everything below Pick a Number Button
                modifier = modifier
                    .fillMaxSize(),
            ) {
                if (!uiState.value.isGameOver) { // <------------------------------ Game is Not Over
                    Row( // <------------ Container for Row of Verify a called number Box and Button
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextField( // <-------------------------------- TextField for Verify Numbers
                            enabled = !uiState.value.isGameOver,
                            label = {
                                if (uiState.value.isVerifyTextValid) {
                                    Text(text = stringResource(R.string.txt_verify_numbers_csv))
                                } else {
                                    Text(text = stringResource(R.string.txt_entry_is_invalid))
                                }
                            },
                            modifier = modifier
                                .padding(end = 10.dp),
                            isError = !uiState.value.isVerifyTextValid,
                            value = uiState.value.verifyNumbersTextField,
                            onValueChange = { vModel.enterVerifyNumbersCsvText(it) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { vModel.doVerifyCalledNumbers() },
                            )
                        )
                        OutlinedIconButton( // <------------------------------------ Done IconButton
                            enabled = !uiState.value.isGameOver,
                            onClick = { vModel.doVerifyCalledNumbers() },
                            modifier = modifier,
                        ) {
                            Icon( // <---------------------------------------------------- Done Icon
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done Button"
                            )
                        }
                    }
                    if (uiState.value.validationResultMessage.isNotBlank()) { // There is a validation result...
                        Row( // <----------------------------------- Container for validation result
                            modifier = modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            if (uiState.value.isNumbersValid) { // Numbers are valid...
                                Icon( // <----------------------------------------------- Valid Icon
                                    modifier = modifier
                                        .testTag("Valid_Icon"),
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = stringResource(R.string.descr_valid_icon),
                                    tint = Color.Green,
                                )
                            } else { // Number(s) not valid...
                                Icon( // <--------------------------------------------- InValid Icon
                                    modifier = modifier
                                        .testTag("Invalid_Icon"),
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = stringResource(R.string.descr_invalid_icon),
                                    tint = Color.Red,
                                )
                            }
                            Spacer(modifier = modifier.width(10.dp)) // <---------- Post Icon Spacer
                            Text(text = uiState.value.validationResultMessage)
                        }
                    }
                    Spacer(modifier = modifier.height(10.dp)) // <--- Post Validation section Spacer
                }
                if (uiState.value.pastPicks.isNotEmpty()) { // Number(s) have been picked...
                    Button( // <-------------------------------------------------- Reset Game Button
                        modifier = modifier
                            .testTag("ResetGame_Button")
                            .padding(start = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        onClick = { vModel.resetGame() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.tertiary,
                        ),
                    ) {
                        Text(text = stringResource(R.string.txt_reset_game))
                    }
                }
                Spacer(modifier = modifier.height(10.dp)) // <------------- Post Reset Button Spacer
                Row(
                    // <-------------------------------------------------- Container for Past Picks
                    modifier = modifier
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text( // <------------------------------------------------------ Past Picks Text
                        text = stringResource(R.string.txt_past_picks),
                        color  = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Divider( // <----------------------------------------------- Post Past Picks Divider
                    modifier = modifier,
                    thickness = 5.dp,
                )
                Column( // <----------------------------------------------- Container for Past Picks
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyVerticalStaggeredGrid( // <-------------------------- Past Pick Results Grid
                        columns = StaggeredGridCells.Fixed(4),
                        content = {
                            items(uiState.value.pastPicks) {
                                PastPick(pick = it)
                            }
                        },
                        contentPadding = PaddingValues(5.dp),
                    )
                }
            }
        }
    }
}