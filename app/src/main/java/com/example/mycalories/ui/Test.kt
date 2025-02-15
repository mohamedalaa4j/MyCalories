package com.example.mycalories.ui

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycalories.domain.model.getFoodList

@Composable
fun TestLazy(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val list by viewModel.lazyList.collectAsState()
    val foodList by viewModel.foodListState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {

        itemsIndexed(foodList) { index, food ->
            val isSelected = list.contains(index)
            Log.i("myTag","isSelected $isSelected : index = $index || list = $list")
//            val isSelected = remember {
//                mutableStateOf(
//                    list.contains(index)
//                )
//            }

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Color.LightGray else Color.White, label = "",
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .background(
                        if (isSelected) Color.LightGray else Color.White
                    )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .pointerInput(isSelected) {
                            detectTapGestures(
                                onLongPress = {
                                    if (isSelected) {
                                        viewModel.removeLazy(index)
                                        Log.i("myTag", "isSelected true")
                                        Log.i("myTag", "==========")

                                    } else {
                                        viewModel.addLazy(index)
                                        Log.i("myTag", "isSelected false")
                                        Log.i("myTag", "==========")

                                    }
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

    }
}