package com.example.zealcasestudy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zealcasestudy.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            val dataFlow = repository.getData()

            dataFlow
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.i("MainViewModel", "Error: ${it.message}")
                }.collect { value ->
                    Log.i("MainViewModel", "Collected: $value")
                }
        }
    }
}
