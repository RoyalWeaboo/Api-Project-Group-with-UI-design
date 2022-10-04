package com.example.api_project_group

import retrofit2.Call
import retrofit2.http.*

interface RestfulAPIUser {
    @GET("user")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

    @POST("user")
    fun addUser(@Body request : User) : Call<ResponseDataUserItem>

    @PUT("user/{id}")
    fun putUser(@Path("id") id:Int, @Body request: User) : Call<List<ResponseDataUserItem>>
}