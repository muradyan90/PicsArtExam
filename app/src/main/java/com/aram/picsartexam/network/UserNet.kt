package com.aram.picsartexam.network

import com.squareup.moshi.Json

data class UserNet (
    @Json(name = "status")
    val status: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "username")
    val username: String?,
    @Json(name = "photo")
    val photo: String?,
    @Json(name = "default_profile_photo")
    val defoultPhoto: Boolean?,
    @Json(name = "email")
    val email: String?
)
data class BodyData(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String
)