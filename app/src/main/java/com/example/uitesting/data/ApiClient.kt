package com.example.uitesting.data

import com.example.uitesting.BuildConfig
import com.example.uitesting.domain.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URL

private const val GET_PRODUCTS_URL = "${BuildConfig.API_BASE_URL}/api/products"
private const val GET_PRODUCT_URL = "${BuildConfig.API_BASE_URL}/api/products/{id}"

object ApiClient {

    suspend fun getProducts() = withContext(Dispatchers.IO) {
        try {
            val json = URL(GET_PRODUCTS_URL).readText()
            Result.success(Json.decodeFromString<List<Product>>(json))
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }

    suspend fun getProduct(id: String) = withContext(Dispatchers.IO) {
        try {
            val json = URL(GET_PRODUCT_URL.replace("{id}", id)).readText()
            Result.success(Json.decodeFromString<Product>(json))
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}
