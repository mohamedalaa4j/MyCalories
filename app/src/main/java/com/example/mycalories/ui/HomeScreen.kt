package com.example.mycalories.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.getFoodList
import com.example.mycalories.ui.theme.MyCaloriesTheme

@Composable
fun HomeScreen(
    foodList: List<FoodItemModel>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp),
            text = foodList.sumOf { it.calories }.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))
        FoodListView(foodList)

        ButtonView()
    }
}

@Composable
fun FoodListView(foodList: List<FoodItemModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(foodList) { food ->
            FoodItemView(food = food)
        }
    }
}

@Composable
fun FoodItemView(food: FoodItemModel) {
    Text(
        text = "${food.name} ${food.calories} cal",
        fontSize = 14.sp
    )
}

@Composable
fun ButtonView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(modifier = Modifier
            .size(70.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(fontSize = 12.sp, text = "Add")
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
fun Preview() {
    MyCaloriesTheme {
        HomeScreen(getFoodList())
    }
}