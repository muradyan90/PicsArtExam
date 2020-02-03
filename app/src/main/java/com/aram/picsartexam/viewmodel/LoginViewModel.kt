package com.aram.picsartexam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aram.picsartexam.repository.Repository
import com.aram.picsartexam.ui.User
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: Repository) : ViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user



    internal fun loginUser(userName: String, password: String) {
        viewModelScope.launch {
            val t = repo.login(userName, password)
           // repo.login(userName, password)
            _user.value = t
        }

    }

}