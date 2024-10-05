package com.example.mycalories.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mycalories.R
import com.example.mycalories.domain.model.FoodItemModel
import com.example.mycalories.domain.model.getFoodList
import com.example.mycalories.ui.theme.MyCaloriesTheme

@Composable
fun FoodSearchView(modifier: Modifier = Modifier, foodList: List<FoodItemModel> = getFoodList(),onItemClick: (item: FoodItemModel)-> Unit) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.teal_700))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        var textInput by rememberSaveable { mutableStateOf("") }

        var isFocused by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus() // Clears focus when IME action is clicked
                }
            ),
            value = textInput,
            onValueChange = { textInput = it }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (isFocused) 1F else 0F)
        ) {
            LazyColumn {
                items(
                    foodList.filter {
                        it.name.lowercase().contains(textInput)
                    }.sortedBy { it.name }
                ) { food ->
                    FoodsItemView(item = food){
                        onItemClick(food)
                        textInput = food.name
                        focusManager.clearFocus()
                    }
                }
            }
        }
    }

}

@Composable
fun FoodsItemView(item: FoodItemModel,onItemClick: ()-> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {onItemClick()},
    ) {
        val (txtName, txtCal) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(txtName) {
                    start.linkTo(parent.start)
                    end.linkTo(txtCal.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .padding(8.dp),
            fontSize = 18.sp,
            text = item.name
        )

        Text(
            modifier = Modifier
                .constrainAs(txtCal) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(8.dp),
            fontSize = 14.sp,
            text = "${item.calories} cal"
        )


        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(colorResource(id = R.color.teal_200))
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
fun MyPreview() {
    MyCaloriesTheme {
        FoodSearchView{}
//        FoodsItemView()
    }
}