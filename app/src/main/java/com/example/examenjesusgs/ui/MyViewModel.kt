package com.example.examenjesusgs.ui

import androidx.compose.animation.core.animateIntSizeAsState
import androidx.lifecycle.ViewModel
import com.example.amazon.data.Producto
import com.example.examenjesusgs.data.MyUiState
import com.example.examenjesusgs.data.ProductoCesta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    fun anadirCarrito(producto: Producto) {
        setAccion("Se añade una unidad a ${producto.nombre}")
        var encotrado = false
        for (productoFor in uiState.value.carrito){
            if (producto.nombre.equals(productoFor.nombre)){
                encotrado = true
                addProductoCesta(ProductoCesta(producto.nombre,productoFor.cantidad+1),true)
                break
            }
        }
        if (encotrado == false){
            addProductoCesta(ProductoCesta(producto.nombre,1),false)
        }
    }

    fun setAccion(newaccion:String){
        _uiState.update {currentState ->
            currentState.copy(
                accion = newaccion
            )
        }
    }

    fun addProductoCesta(newProducto: ProductoCesta,elimino: Boolean){
        val newLista = uiState.value.carrito
        if (elimino == true){
            newLista.remove(ProductoCesta(newProducto.nombre,newProducto.cantidad-1))
            newLista.add(newProducto)
        }else{
            newLista.add(newProducto)
        }
        _uiState.update {currentState ->
            currentState.copy(
                carrito = newLista
            )
        }
    }

    fun quitProductoCesta(){
        if (uiState.value.carrito.isNotEmpty()){
            val newLista = uiState.value.carrito
            setAccion("Se a eliminado ${newLista.first().nombre}")
            newLista.removeAt(0)
        }
    }

    fun compruebaCambioCatalogo() {
        if (uiState.value.accion.contains("eliminado")){
            _uiState.update {currentState ->
                currentState.copy(
                    accion = "Ninguna"
                )
            }
        }
    }

    fun compruebaCambioCarrito() {
        if (uiState.value.accion.contains("añade")){
            _uiState.update {currentState ->
                currentState.copy(
                    accion = "Ninguna"
                )
            }
        }
    }

}