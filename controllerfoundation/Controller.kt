package com.example.controllerfoundation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class ScreenNames {
    HOME,
    PAGE,
}

class MyViewModel : ViewModel() {
    var currentIndex by mutableStateOf(0)
}

@Composable
fun Controllers(navController: NavHostController = rememberNavController()) {
    val viewModel: MyViewModel = viewModel()
    NavHost(navController = navController, startDestination = ScreenNames.HOME.name) {
        composable(route = ScreenNames.HOME.name) {
            Home(navController = navController, itemS = listOfItems)
        }
        composable(route = ScreenNames.PAGE.name) {
            ItemPage(itemS = listOfItems, navController = navController, viewModel= viewModel)
        }
    }
}
@Composable
fun Home(navController: NavHostController, itemS: List<Item>) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(itemS) { item ->
                    Card(name = item.name, price = item.price, img = item.imageResource)
                }
            }
            Button(
                onClick = {
                    navController.navigate(ScreenNames.PAGE.name)
                },
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Card(name: String, price: String, img: Int) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = name)
            Text(text = price)
        }
        Image(painter = painterResource(img), contentDescription = name)
    }
}

@Composable
fun ItemPage(navController: NavHostController, itemS: List<Item>, viewModel: MyViewModel) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(itemS[viewModel.currentIndex].name)
            Text(itemS[viewModel.currentIndex].info)
            Text(itemS[viewModel.currentIndex].price)

            Image(
                painter = painterResource(itemS[viewModel.currentIndex].imageResource),
                contentDescription = itemS[viewModel.currentIndex].name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (viewModel.currentIndex > 0) {
                            viewModel.currentIndex--
                        }
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = {
                        if (viewModel.currentIndex < itemS.size - 1) {
                            viewModel.currentIndex++
                            navController.navigate(ScreenNames.PAGE.name)
                        } else {
                            viewModel.currentIndex = 0
                            navController.navigate(ScreenNames.HOME.name)
                        }
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}
