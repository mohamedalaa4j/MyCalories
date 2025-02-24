package com.example.mycalories.ui.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycalories.domain.model.HistoryModel
import com.example.mycalories.domain.model.RecordDate
import com.example.mycalories.domain.model.TotalsModel
import com.example.mycalories.ui.theme.MyCaloriesTheme
import com.example.mycalories.utils.ComposeLifecycleHelper


@Composable
fun HistoryScreen(
    recordsList: List<HistoryModel>?,
    fetchRecords: () -> Unit
) {
    ComposeLifecycleHelper(
        onCreate = {
            fetchRecords()
        }
    )
    Column (
        modifier = Modifier.fillMaxSize(),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                modifier = Modifier.width(85.dp),
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Date",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Calories",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Protein",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Carp",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Fat",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            recordsList?.let {
                itemsIndexed(it) { _, record ->
                    HistoryListItem(record)
                }
            }
        }
    }
}


@Composable
fun HistoryListItem(item: HistoryModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
            modifier = Modifier.width(85.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = item.date.date,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .width(1.dp)  // Line width
                    .fillMaxHeight()
                    .background(Color.Black)  // Line color
            )
        }

        Text(
            text = item.total.calories.toInt().toString(),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = item.total.protein.toInt().toString(),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = item.total.carp.toInt().toString(),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = item.total.fat.toInt().toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
fun Preview() {
    val fakeList = listOf(
        HistoryModel(
            date = RecordDate("2022-02-02"),
            total = TotalsModel(2200.0, 110.0, 260.0, 70.0)
        ),

        HistoryModel(
            date = RecordDate("Today"),
            total = TotalsModel(2200.0, 110.0, 260.0, 70.0)
        ),


        )
    MyCaloriesTheme {
        HistoryScreen(
            fakeList
        ) {}
    }
}