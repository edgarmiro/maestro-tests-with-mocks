package com.example.uitesting.data

import com.example.uitesting.domain.Product

object FakeData {

    val product1 = Product(
        id = "1",
        name = "Iceberg lettuce",
        imageUrl = "https",
        pvp = 0.99,
    )

    val product2 = Product(
        id = "2",
        name = "Hacendado Classic Chickpea Hummus Recipe",
        imageUrl = "https",
        pvp = 1.05,
    )

    val products = listOf(product1, product2)
}
