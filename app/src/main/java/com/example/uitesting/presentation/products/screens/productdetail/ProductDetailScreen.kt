@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uitesting.presentation.products.screens.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun ProductDetailScreen(id: String) {
    val state = rememberProductDetailScreenState(id)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(R.string.product_detail)) })
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            Screen(state)
        }
    }
}

@Composable
private fun Screen(state: ProductDetailState) {
    when (state) {
        is ProductDetailState.Loading -> Loading()
        is ProductDetailState.Active -> ProductData(state.product)
        is ProductDetailState.Error -> Error(stringResource(R.string.product_detail_error))
    }
}

@Composable
private fun ProductData(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineSmall,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    rememberAsyncImagePainter(product.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${product.pvp} €",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview(@PreviewParameter(ProductDetailStateProvider::class) state: ProductDetailState) {
    AppTheme {
        Screen(
            state = state,
        )
    }
}
