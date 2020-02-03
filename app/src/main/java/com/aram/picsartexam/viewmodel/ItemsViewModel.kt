package com.aram.picsartexam.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aram.picsartexam.ExamApplication
import com.aram.picsartexam.repository.Repository
import com.aram.picsartexam.ui.DataResponce
import com.aram.picsartexam.ui.User
import com.aram.picsartexam.utils.LOG
import kotlinx.coroutines.launch

class ItemsViewModel(private val repo: Repository) : ViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user
  private var _networkStatus = MutableLiveData<Boolean>()
    val networkStatus: LiveData<Boolean>
        get() = _networkStatus

   private var _responce = MutableLiveData<DataResponce>()
    val responce: LiveData<DataResponce>
        get() = _responce

    init {
        if(ExamApplication.isNetworkConnected()){
            getItems()
            _networkStatus.value = true
        }else{
            _networkStatus.value = false

        }
    }



    internal fun getItems() {
        viewModelScope.launch {
            var t = repo.getItems()
            Log.d(LOG," viem model get metod $t")
          //_responce.postValue(repo.getItems())
          _responce.value = t
        }

    }
}