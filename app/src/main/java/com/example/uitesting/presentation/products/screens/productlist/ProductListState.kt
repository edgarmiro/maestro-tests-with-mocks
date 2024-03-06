package com.example.uitesting.presentation.products.screens.productlist

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

sealed interface ProductListState {
    data object Loading : ProductListState
    data object Empty : ProductListState
    data object Error : ProductListState
    data class Active(val products: List<Product>) : ProductListState
}

@Composable
fun rememberProductListScreenState(): ProductListState {
    var state by remember { mutableStateOf<ProductListState>(ProductListState.Loading) }

    LaunchedEffect(Unit) {
        ApiClient.getProducts().fold(
            onSuccess = {
                state = if (it.isEmpty()) ProductListState.Empty else ProductListState.Active(it)
            },
            onFailure = { state = ProductListState.Error },
        )
    }

    return state
}

class ProductListStateProvider : PreviewParameterProvider<ProductListState> {
    override val values = sequenceOf(
        ProductListState.Loading,
        ProductListState.Empty,
        ProductListState.Active(FakeData.products),
        ProductListState.Error,
    )
}
