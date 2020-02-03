package com.aram.picsartexam.repository

import com.aram.picsartexam.network.ApiService
import com.aram.picsartexam.network.BodyData
import com.aram.picsartexam.network.Post
import com.aram.picsartexam.ui.DataResponce
import com.aram.picsartexam.ui.User
import com.aram.picsartexam.utils.responceAsUiResponce
import com.aram.picsartexam.utils.userNetAsUserUi

class Repository(private val apiService: ApiService) {

    private var querryMapUser = mutableMapOf<String, String>()
    private var querryMapItems = mutableMapOf(
        "q" to "offset",
        "limit" to "50"
    )

    suspend fun login(userName: String, password: String): User {

        querryMapUser["username"] = userName
        querryMapUser["password"] = password


        return apiService.loginUser(BodyData(userName,password)).await().userNetAsUserUi()
    }

    suspend fun getItems(): DataResponce {
        return apiService.getItems(querryMapItems).await().responceAsUiResponce()
    }

}