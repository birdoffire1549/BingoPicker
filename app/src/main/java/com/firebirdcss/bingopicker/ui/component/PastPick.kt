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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.firebirdcss.bingopicker.R
import com.firebirdcss.bingopicker.data.blankBallImages
import com.firebirdcss.bingopicker.data.poko.Pick
import com.firebirdcss.bingopicker.data.poko.PickBall

@Preview
@Composable
fun PastPick(
    pick: Pick = Pick(),
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(60.dp)
            .width(100.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "#${pick.sequence}",
                    fontSize = if (pick.sequence > 9) {
                        10.sp
                    } else {
                        15.sp
                    }
                )
            }
            Column(
                modifier = modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = pick.ball.ballImage), contentDescription = null)
                    Text(
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