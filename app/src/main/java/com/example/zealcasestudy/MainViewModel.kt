package com.example.zealcasestudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zealcasestudy.api.Repository
import com.example.zealcasestudy.model.Lottery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _data = MutableStateFlow<List<Lottery>?>(null)
    val data: StateFlow<List<Lottery>?> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getData().collect { data ->
                _data.value = data
            }
        }
    }
}
