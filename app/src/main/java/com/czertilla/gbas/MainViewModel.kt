package com.czertilla.gbas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czertilla.gbas.data.local.session.SessionManager
import com.czertilla.gbas.domain.model.LoggedInUser
import com.czertilla.gbas.data.user.UserStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userStorage: UserStorage,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _user = MutableLiveData<LoggedInUser>()
    val user: LiveData<LoggedInUser> get() = _user

    fun loadUser() {
        val userId = sessionManager.userId ?: return
        viewModelScope.launch {
            val user = userStorage.getUserEntity(userId)
            user.let {
                _user.postValue(it)
            }
        }
    }
}
