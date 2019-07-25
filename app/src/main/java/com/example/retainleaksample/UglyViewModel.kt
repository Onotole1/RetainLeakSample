package com.example.retainleaksample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class UglyViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _uglyLiveData = MutableLiveData<String>()

    val uglyLiveData: LiveData<String>
        get() = _uglyLiveData

    init {
        uiScope.launch {
            _uglyLiveData.value = uglyWork()
        }
    }

    private suspend fun uglyWork(): String =
        withContext(Dispatchers.IO) {
            delay(5000)
            "LOL"
        }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}