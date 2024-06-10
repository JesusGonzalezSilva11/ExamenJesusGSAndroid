package com.example.examenjesusgs.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.examenjesusgs.data.MyUiState
@Composable
fun Pantalla2(
    viewModel: MyViewModel,
    uiState: MyUiState,
    navController: () -> Unit
    ) {
    viewModel.compruebaCambioCarrito()
    Column {
        Text(text = "CARRITO")
        carrito(uiState = uiState)
        Text(text = "ACCION ELIMINAR")
        Text(text = uiState.accion)
        Button(onClick = { viewModel.quitProductoCesta() }) {
            Text(text = "Eliminar primer producto del carrito")
        }
        Button(onClick = navController) {
            Text(text = "Ir a pantalla productos originales")
        }
    }

}

@Composable
fun carrito(uiState: MyUiState){
    Column {
        for (producto in uiState.carrito){
            Text(text = "Producto: ${producto.nombre}, unidades: ${producto.cantidad}")
        }
    }
}