package com.example.mycalories.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.mycalories.domain.model.TotalsModel
import com.example.mycalories.ui.theme.MyCaloriesTheme
import com.example.mycalories.utils.triggerVibration
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val foodList by viewModel.foodListState.collectAsState()
    val totalsState by viewModel.totalsState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedItemsIndex by remember { mutableStateOf(setOf<Int>()) }
    var deleteButtonVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TotalView(totalsState)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            SelectableList(
                foodList = foodList,
                selectedItems = selectedItemsIndex,
                onSelectionChange = { index ->
                    deleteButtonVisibility = !deleteButtonVisibility
                    selectedItemsIndex = if (index in selectedItemsIndex) {
                        selectedItemsIndex - index
                    } else {
                        selectedItemsIndex + index
                    }
                }
            )

            ButtonView(
                onAddClick = { showAddDialog = !showAddDialog },
                onDeleteClick = {
                    viewModel.deleteSelectedItems(selectedItemsIndex)
                    selectedItemsIndex = emptySet()
                    deleteButtonVisibility = !deleteButtonVisibility
                },
                deleteButtonVisibility = deleteButtonVisibility
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
fun TotalView(totals: TotalsModel) {
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
            text = totals.calories.toString(),
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
            text = "${totals.protein} Protein - ${totals.carp} carp - ${totals.fat} fat",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SelectableList(
    foodList: List<FoodItemModel>,
    selectedItems: Set<Int>,
    onSelectionChange: (Int) -> Unit,
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 90.dp,
        )
    ) {

        itemsIndexed(foodList) { index, food ->
            FoodItemView(
                food = food,
                isSelected = index in selectedItems,
                onLongPress = {
                    triggerVibration(context)
                    onSelectionChange(index)
                }

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodItemView(
    food: FoodItemModel,
    isSelected: Boolean,
    onLongPress: () -> Unit
) {
    val multiplicationCoefficient = BigDecimal(food.weight / 100.0)
    val totalCalories = BigDecimal(food.calories).multiply(multiplicationCoefficient).setScale(2, RoundingMode.HALF_UP)
    val totalProtein = BigDecimal(food.protein).multiply(multiplicationCoefficient).setScale(2, RoundingMode.HALF_UP)
    val totalCarp = BigDecimal(food.carp).multiply(multiplicationCoefficient).setScale(2, RoundingMode.HALF_UP)
    val totalFat = BigDecimal(food.fat).multiply(multiplicationCoefficient).setScale(2, RoundingMode.HALF_UP)

    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .background(if (isSelected) Color.Gray else Color.White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .combinedClickable(
                    onClick = {},
                    onLongClick = onLongPress
                ),
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
                    text = "$totalProtein Protein - $totalCarp carp - $totalFat fat",
                    fontSize = 12.sp,
                )

                Text(
                    modifier = Modifier.constrainAs(calories) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                    text = "$totalCalories Cal",
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
    deleteButtonVisibility: Boolean
) {
    Box(
        modifier = Modifier.padding(18.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            onClick = onAddClick
        ) {
            Text(fontSize = 12.sp, text = "Add")
        }

        if (deleteButtonVisibility) {
            FloatingActionButton(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                containerColor = Color.Red,
                contentColor = Color.White,
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
                    text = calculateWeightCalories(selectedItem?.calories, weightInput) + " cal",
                    fontWeight = FontWeight.Bold
                )
                Button(
                    modifier = Modifier.constrainAs(btnAdd) {
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    onClick = {
                        selectedItem?.weight = weightInput.toDouble()
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
        val calculated = calories?.times((weight.toDouble() / 100.0)) ?: 0.0
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