package com.aram.picsartexam.utils

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aram.picsartexam.network.DataResponceNet
import com.aram.picsartexam.network.ItemNet
import com.aram.picsartexam.network.UserNet
import com.aram.picsartexam.ui.DataResponce
import com.aram.picsartexam.ui.Item
import com.aram.picsartexam.ui.RVAdapter
import com.aram.picsartexam.ui.User
import com.bumptech.glide.Glide

val LOG = "TAG"
fun UserNet.userNetAsUserUi(): User {
    return User(
        status = status,
        id = id,
        name = name,
        username = username,
        photo = photo,
        defoultPhoto = defoultPhoto,
        email = email
    )
}

fun DataResponceNet.responceAsUiResponce(): DataResponce {
    return DataResponce(
        status = status,
        itemsList = itemsList?.map {
            it.itemNetAsItemUi()
        }
    )
}


fun ItemNet.itemNetAsItemUi(): Item {
    return Item(
        id = id,
        url = url,
        title = title
    )
}

@BindingAdapter("imageUrl")
fun bindeImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context).load(imageUrl.toUri()).into(imageView)
    }
}

@BindingAdapter("itemsList")
fun bindRecyclerView(recyclerView: RecyclerView, itemList: List<Item>?) {
    Log.d(LOG,"list $itemList")
    itemList?.let {
        Log.d(LOG,"list $itemList")
        (recyclerView.adapter as RVAdapter).itemsList = itemList.toMutableList()
    }
}
