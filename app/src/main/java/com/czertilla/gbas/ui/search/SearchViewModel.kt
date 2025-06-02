package com.czertilla.gbas.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czertilla.gbas.domain.model.ServiceCard
import com.czertilla.gbas.domain.usecase.GetServicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getServicesUseCase: GetServicesUseCase
) : ViewModel() {
    private val _services = MutableLiveData<List<ServiceCard>>()
    val services: LiveData<List<ServiceCard>> = _services

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        _isRefreshing.value = false
        loadCachedServices()
        refreshServices()
    }

    fun loadCachedServices() {
        viewModelScope.launch {
            _services.value = getServicesUseCase(false).map { it }
        }
    }

    fun refreshServices() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _services.value = getServicesUseCase(true).map { it }
            _isRefreshing.value = false
        }
    }
}