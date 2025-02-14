package com.example.mycalories.ui

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.ui.theme.MyCaloriesTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val foodList by viewModel.foodListState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TotalView(foodList)

        Box(
            modifier = Modifier.fillMaxSize().weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            FoodListView(foodList = foodList,
                onLongPress = { isDeleting = !isDeleting }
            )

            ButtonView(
                onAddClick = { showAddDialog = !showAddDialog },
                onDeleteClick = {},
                isDeleting = isDeleting
            )
        }

        if (showAddDialog) {
            AddDialog(
                onAddClick = {
                    viewModel.addFoodItem(it)
                    showAddDialog = !showAddDialog
                },
                onDismissClick = { showAddDialog = !showAddDialog }
            )
        }
    }
}

@Composable
fun TotalView(foodList: List<FoodItemModel>) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (caloriesCount, caloriesTitle, macros) = createRefs()

        Text(
            modifier = Modifier.constrainAs(caloriesCount) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = foodList.sumOf { it.calories }.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.constrainAs(caloriesTitle) {
                top.linkTo(caloriesCount.top)
                bottom.linkTo(caloriesCount.bottom)
                start.linkTo(caloriesCount.end, margin = 8.dp)
            },
            text = "Cal",
            fontSize = 12.sp,
        )

        Text(
            modifier = Modifier.constrainAs(macros) {
                top.linkTo(caloriesCount.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "${foodList.sumOf { it.protein }} Protein - ${foodList.sumOf { it.carp }} carp - ${foodList.sumOf { it.fat }} fat",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FoodListView(foodList: List<FoodItemModel>, onLongPress: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 90.dp,
        )
    ) {
        items(foodList) { food ->
            FoodItemView(
                food = food,
                onLongPress = onLongPress
            )
        }
    }
}

@Composable
fun FoodItemView(
    food: FoodItemModel,
    onLongPress: () -> Unit
) {
    val haptics = LocalHapticFeedback.current
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.LightGray else Color.White,
        label = "BackgroundColorAnimation"
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .background(backgroundColor)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            isSelected = !isSelected
                            onLongPress()
                        }
                    )
                },
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                val (name, macros, calories) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(name) {
                        top.linkTo(parent.top)
                        bottom.linkTo(macros.top)
                        start.linkTo(parent.start)
                        end.linkTo(calories.start)
                        width = Dimension.fillToConstraints
                    },
                    text = food.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.constrainAs(macros) {
                        top.linkTo(name.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(calories.start)
                        width = Dimension.fillToConstraints
                    },
                    text = "${food.protein} Protein - ${food.carp} carp - ${food.fat} fat",
                    fontSize = 12.sp,
                )

                Text(
                    modifier = Modifier.constrainAs(calories) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                    text = "${food.calories} Cal",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}

@Composable
fun ButtonView(
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit,
    isDeleting: Boolean
) {
    Box(
        modifier = Modifier.padding(18.dp)
    ) {
        Button(
            modifier = Modifier.size(84.dp),
            onClick = onAddClick
        ) {
            Text(fontSize = 12.sp, text = "Add")
        }

        if (isDeleting) {
            Button(
                modifier = Modifier.size(84.dp),
                colors = ButtonColors(
                    Color.Red,
                    Color.White,
                    Color.Red,
                    Color.Red,
                ),
                onClick = onDeleteClick
            ) {
                Text(fontSize = 12.sp, text = "Delete")
            }
        }
    }
}

@Composable
fun AddDialog(
    onAddClick: (item: FoodItemModel) -> Unit,
    onDismissClick: () -> Unit
) {
    var selectedItem: FoodItemModel? by remember { mutableStateOf(null) }

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        onDismissRequest = {
            selectedItem = null
            onDismissClick()
        },

        ) {
        val focusManager = LocalFocusManager.current

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            shape = RoundedCornerShape(16.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                val (searchSelector, tfWeight, txtCalories, btnAdd) = createRefs()
                val focusRequester = remember { FocusRequester() }
                var weightInput by rememberSaveable { mutableStateOf("100") }
                var isFocused by remember { mutableStateOf(false) }

                TextField(
                    modifier = Modifier
                        .constrainAs(tfWeight) {
                            top.linkTo(parent.top, margin = 90.dp)
                            start.linkTo(parent.start)
                            width = Dimension.wrapContent

                        }
                        .width(100.dp)
                        .height(60.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            if (focusState.isFocused) weightInput = ""
                            if (!focusState.isFocused && weightInput == "") weightInput = "100"
                        },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),

                    value = weightInput,
                    onValueChange = {
                        weightInput = it
                    }
                )

                Text(
                    modifier = Modifier.constrainAs(txtCalories) {
                        top.linkTo(tfWeight.top)
                        bottom.linkTo(tfWeight.bottom)
                        end.linkTo(parent.end)
                    },
                    text = calculateWeightCalories(selectedItem?.calories, weightInput),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    modifier = Modifier.constrainAs(btnAdd) {
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    onClick = {

                        onAddClick(selectedItem!!)
                    }
                ) {
                    Text(text = "Add")
                }


                FoodSearchView(
                    modifier = Modifier
                        .constrainAs(searchSelector) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    onItemClick = { item ->
                        selectedItem = item
                        focusManager.clearFocus()
                    },
                    onKeyboardDone = {
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}


fun calculateWeightCalories(calories: Double?, weight: String): String {
    return if (weight == "" || weight == "0") {
        "0.0"
    } else {
        val calculated = calories?.times((weight.toDouble() / 100.0))
        return calculated.toString()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
fun Preview() {
    MyCaloriesTheme {
        HomeScreen()
    }
}