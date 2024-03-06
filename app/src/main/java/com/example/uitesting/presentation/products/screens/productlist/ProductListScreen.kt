@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uitesting.presentation.products.screens.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.uitesting.R
import com.example.uitesting.domain.Product
import com.example.uitesting.presentation.common.components.Error
import com.example.uitesting.presentation.common.components.Loading
import com.example.uitesting.presentation.common.theme.AppTheme

@Composable
fun ProductListScreen(onProductClick: (Product) -> Unit) {
    val state = rememberProductListScreenState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(R.string.product_list)) })
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            Screen(state, onProductClick)
        }
    }
}

@Composable
private fun Screen(state: ProductListState, onProductClick: (Product) -> Unit) {
    when (state) {
        is ProductListState.Loading -> Loading()
        is ProductListState.Empty -> Placeholder()
        is ProductListState.Active -> ProductList(state.products, onProductClick)
        is ProductListState.Error -> Error(stringResource(R.string.product_list_error))
    }
}

@Composable
fun Placeholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(R.string.product_list_placeholder), color = Color.DarkGray)
    }
}

@Composable
private fun ProductList(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(products) {
            ProductCell(
                modifier = Modifier.clickable(onClick = { onProductClick(it) }),
                product = it,
            )
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ProductCell(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            rememberAsyncImagePainter(product.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
        )

        Text(
            modifier = Modifier.weight(1f),
            text = product.name,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview(@PreviewParameter(ProductListStateProvider::class) state: ProductListState) {
    AppTheme {
        Screen(
            state = state,
            onProductClick = {},
        )
    }
}
