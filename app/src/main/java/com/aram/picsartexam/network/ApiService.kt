package com.aram.picsartexam.network

import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {
//    @POST("/user/signin.json")
//    fun loginUser(@Body post: Post): Deferred<UserNet>
    //@FormUrlEncoded
    @POST("https://api.picsart.com/users/signin.json")
    fun loginUser(@Body body: BodyData): Deferred<UserNet>

    @GET("/stage/photos/freetoedit/search.json")
    fun getItems(@QueryMap queryParams: Map<String,String>): Deferred<DataResponceNet>
}