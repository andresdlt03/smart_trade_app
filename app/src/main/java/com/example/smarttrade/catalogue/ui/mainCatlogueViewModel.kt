package com.example.smarttrade.catalogue.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class productManagementViewModel : ViewModel() {

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

}