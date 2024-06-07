package com.example.smarttrade.orders.OrdersHistory

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smarttrade.orders.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersHistoryViewModel @Inject constructor(): ViewModel() {

    private val _orderSelected: MutableState<Order?> = mutableStateOf(null)
    val orderSelected get() = _orderSelected

    fun updateOrderSelected(order: Order){
        _orderSelected.value = order
    }

}