package com.example.examenjesusgs.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.example.amazon.data.DataSource
import com.example.examenjesusgs.data.MyUiState

@Composable
fun Pantalla1(
    viewModel: MyViewModel,
    uiState: MyUiState,
    navController: () -> Unit
    ) {
    Column() {
        viewModel.compruebaCambioCatalogo()
        Text(text = "AÑADIR PRODUCTOS AL CARRITO")
        lista(viewModel = viewModel)
        Text(text = "ACCION AÑADIR")
        Text(text = uiState.accion)
        Button(onClick = navController) {
            Text(text = "Ir a pantalla de carrito")
        }
    }
}

@Composable
fun lista(
    viewModel: MyViewModel,
    modifier: Modifier = Modifier
    ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // columns = GridCells.Adaptive(minSize = x.dp)...
        modifier = modifier
    ){
        items(DataSource.productos){ producto ->
            Card(modifier
                .padding(12.dp)
            ) {
                Column(
                    modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()

                ) {
                    Text(text = "Producto: ${producto.nombre}")
                    Row (
                        modifier
                            .background(Color.LightGray)
                            .fillMaxWidth()
                    ){
                        Button(onClick = { viewModel.anadirCarrito(producto)}) {
                            Text(text = "+")
                        }
                    }

                }
            }
        }
        }

    }