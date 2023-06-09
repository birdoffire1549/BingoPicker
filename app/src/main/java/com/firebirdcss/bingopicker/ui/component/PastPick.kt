package com.firebirdcss.bingopicker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebirdcss.bingopicker.data.poko.Pick

/**
 * COMPOSABLE: This composable is a reusable component which
 * is used to display a picked Bingo Ball.
 */
@Preview
@Composable
fun PastPick(
    modifier: Modifier = Modifier,
    pick: Pick = Pick(),
) {
    OutlinedCard( // <----------------------------------------------------------- The Card Container
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .padding(10.dp)
            .height(60.dp)
            .width(100.dp),
    ) {
        Row( // <------------------------------------ Primary container for the contents of the card
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column( // <------------------------------------------ Container for the Sequence Number
                modifier = modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text( // <------------------------------------------ The text of the sequence number
                    text = "#${pick.sequence}",
                    fontSize = if (pick.sequence > 9) { // Pick is double digit...
                        10.sp
                    } else { // Pick is single digit...
                        15.sp
                    }
                )
            }
            Column( // <---------------------------------- Container for the Image of the Bingo Ball
                modifier = modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box( // <------------------------------ Container to allow for text overlay of image
                    contentAlignment = Alignment.Center
                ) {
                    Image( // <---------------------------------------------------- Bingo Ball Image
                        painter = painterResource(id = pick.ball.ballImage),
                        contentDescription = null
                    )
                    Text( // <---------------------------------------------- Text for the Bingo Ball
                        text = "${pick.row.name}-${pick.value}",
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(
                            shadow = Shadow(pick.ball.shadowColor, blurRadius = 2f)
                        )
                    )
                }
            }
        }
    }
}