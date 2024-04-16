package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel

abstract class RegisterViewModel : ViewModel() {
    abstract fun onRegister() : Unit
}