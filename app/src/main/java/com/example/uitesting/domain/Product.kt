package com.example.uitesting.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("pvp") val pvp: Double,
)
