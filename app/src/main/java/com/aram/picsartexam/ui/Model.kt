package com.aram.picsartexam.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    val status: String?,
    val id: String?,
    val name: String?,
    val username: String?,
    val photo: String?,
    val defoultPhoto: Boolean?,
    val email: String?
): Parcelable

data class DataResponce(
    val status: String?,
    val itemsList: List<Item>?
)

data class Item(
    val id: String?,
    val url: String?,
    val title: String?
)