package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.model.Seller
import com.example.smarttrade.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerRegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : RegisterViewModel() {

    private val _state = MutableStateFlow(SellerRegisterState())
    val state = _state.asStateFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateSurname(surname: String) {
        _state.value = _state.value.copy(surname = surname)
    }

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun updateCompanyName(companyName: String) {
        _state.value = _state.value.copy(companyName = companyName)
    }

    fun updateCif(cif: String) {
        _state.value = _state.value.copy(cif = cif)
    }

    override fun onRegister() {
        viewModelScope.launch {
            val seller = Seller(
                name = state.value.name,
                surname = state.value.surname,
                email = state.value.email,
                password = state.value.password,
                company = state.value.companyName,
                cif = state.value.cif
            )
            userRepository.registerUser(seller, "seller")
        }
    }
}