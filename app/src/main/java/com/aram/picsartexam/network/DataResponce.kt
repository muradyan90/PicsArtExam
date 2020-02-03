package com.aram.picsartexam.network

import com.squareup.moshi.Json

data class DataResponceNet(
    @Json(name = "status")
    val status: String?,
    @Json(name = "response")
    val itemsList: List<ItemNet>?
)

data class ItemNet(
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "title")
    val title: String?
)

data class Post(
    val username: String?,
    val password: String?
)