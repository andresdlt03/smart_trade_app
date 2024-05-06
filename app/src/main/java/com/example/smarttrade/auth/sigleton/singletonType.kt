package com.example.smarttrade.auth.sigleton

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object SingletonValue {
    private val _value: MutableState<String> = mutableStateOf("")

    var value: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }
}