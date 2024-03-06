package com.example.uitesting.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uitesting.presentation.products.screens.productdetail.ProductDetailScreen
import com.example.uitesting.presentation.products.screens.productlist.ProductListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "product-list",
    ) {
        composable(
            route = "product-list",
        ) {
            ProductListScreen {
                navController.navigate("product-detail/${it.id}")
            }
        }

        composable(
            route = "product-detail/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            val id = requireNotNull(it.arguments?.getString("id"))
            ProductDetailScreen(id)
        }
    }
}
