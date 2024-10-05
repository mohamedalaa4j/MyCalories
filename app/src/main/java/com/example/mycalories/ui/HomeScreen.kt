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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.getFoodList
import com.example.mycalories.ui.theme.MyCaloriesTheme

@Composable
fun HomeScreen(
    foodList: List<FoodItemModel>
) {
    var showAddDialog by remember { mutableStateOf(true) }

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

        ButtonView { showAddDialog = !showAddDialog }

        if (showAddDialog) {
            AddDialog(
                onAddClick = { /*TODO*/ },
                onDismissClick = { showAddDialog = !showAddDialog }
            )
        }
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
fun ButtonView(
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier
                .size(70.dp),
            onClick = onAddClick
        ) {
            Text(fontSize = 12.sp, text = "Add")
        }
    }
}

@Composable
fun AddDialog(
    onAddClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    var selectedItem: FoodItemModel? by remember { mutableStateOf(null) }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                val (searchSelector, tfWeight, txtCalories) = createRefs()

                var weightInput by rememberSaveable { mutableDoubleStateOf(0.0) }

//                TextField(
//                    modifier = Modifier
//                        .constrainAs(tfWeight) {
//                            top.linkTo(parent.top, margin = 350.dp)
//                            start.linkTo(parent.start)
//                        }
//                        .size(60.dp),
//                    keyboardOptions = KeyboardOptions.Default.copy(
//                        imeAction = ImeAction.Done
//                    ),
//                    keyboardActions = KeyboardActions(
//                        onDone = {
////                            focusManager.clearFocus() // Clears focus when IME action is clicked
//                        }
//                    ),
//
//                            value = weightInput.toString(),
//                    onValueChange = { weightInput = it.toDouble() }
//                )

                TextField(
                    modifier = Modifier.constrainAs(tfWeight){
                        top.linkTo(parent.top, margin = 350.dp)
                        start.linkTo(parent.start)
                    },
                    value = "",
                    onValueChange = {}
                )

                Text(
                    modifier = Modifier.constrainAs(txtCalories) {
                        top.linkTo(tfWeight.top)
                        bottom.linkTo(tfWeight.bottom)
                        end.linkTo(parent.end)
                    },
                    text = (selectedItem?.calories?.times(weightInput)).toString(),
                    fontWeight = FontWeight.Bold
                )
//                Button(
//                    onClick = onDismissClick
//                ) {
//                    Text(text = "Hide")
//                }


                FoodSearchView(
                    modifier = Modifier.constrainAs(searchSelector) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) { item ->
                    selectedItem = item
                }
            }
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