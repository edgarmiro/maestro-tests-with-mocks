package com.example.uitesting.presentation.products.screens.productdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.uitesting.data.ApiClient
import com.example.uitesting.data.FakeData
import com.example.uitesting.domain.Product

sealed interface ProductDetailState {
    data object Loading : ProductDetailState
    data object Error : ProductDetailState
    data class Active(val product: Product) : ProductDetailState
}

@Composable
fun rememberProductDetailScreenState(id: String): ProductDetailState {
    var state by remember { mutableStateOf<ProductDetailState>(ProductDetailState.Loading) }

    LaunchedEffect(id) {
        ApiClient.getProduct(id).fold(
            onSuccess = { state = ProductDetailState.Active(it) },
            onFailure = { state = ProductDetailState.Error },
        )
    }

    return state
}

class ProductDetailStateProvider : PreviewParameterProvider<ProductDetailState> {
    override val values = sequenceOf(
        ProductDetailState.Loading,
        ProductDetailState.Active(FakeData.product1),
        ProductDetailState.Error,
    )
}
