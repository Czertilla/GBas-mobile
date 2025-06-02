package com.czertilla.gbas.ui.service;

import android.util.Log
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.czertilla.gbas.data.ServiceRepositoryImpl;
import com.czertilla.gbas.domain.model.ServicePage;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

@HiltViewModel
class ServiceViewModel @Inject
constructor(
    private val repository: ServiceRepositoryImpl
) : ViewModel() {

    private val _servicePage = MutableLiveData<ServicePage?>()
    val servicePage: LiveData<ServicePage?> = _servicePage


     fun bindServiceById(id: String){
        viewModelScope.launch {
            try {
                _servicePage.value = repository.getServicePage(UUID.fromString(id))
            } catch (e: HttpException) {
                Log.e("ServiceViewModel", "Ошибка HTTP: ${e.code()}")
                _servicePage.value = null // или не выставляй вообще
            } catch (e: Exception) {
                Log.e("ServiceViewModel", "Ошибка запроса", e)
            }
        }
    }
}