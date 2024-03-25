package com.example.controllerfoundation

import androidx.annotation.DrawableRes

data class Item (
    @DrawableRes val imageResource:Int,
    val name : String,
    val info : String,
    val price : String,
    val index : Int,
)

val listOfItems = listOf(
    Item(
        index = 1,
        name = "core i9",
        info = "Highest",
        price = "700$",
        imageResource = R.drawable.i9
    ),
    Item(
        index = 1,
        name = "Ryzen 9",
        info = "High Performance",
        price = "600$",
        imageResource = R.drawable.r9
    ),
    Item(
        index = 1,
        name = "Core i7",
        info = "High Performance",
        price = "500$",
        imageResource = R.drawable.i7
    ),
    Item(
        index = 1,
        name = "Ryzen 7",
        info = "Mid Performance",
        price = "400$",
        imageResource = R.drawable.r7
    ),
    Item(
        index = 1,
        name = "Core i5",
        info = "Mid Performance",
        price = "300$",
        imageResource = R.drawable.i5
    ),
    Item(
        index = 1,
        name = "Ryzen 5",
        info = "Entry Level",
        price = "200$",
        imageResource = R.drawable.r5
    )
)
