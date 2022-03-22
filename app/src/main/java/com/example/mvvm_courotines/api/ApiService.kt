package com.example.mvvm_courotines.api

import com.example.mvvm_courotines.models.User
import retrofit2.http.GET
import retrofit2.http.Path

//https://open-api.xyz/placeholder/user/1
interface ApiService {
    @GET("placeholder/user/{userId}")

    suspend fun getUser(
        //Need a path variable
        @Path("userId") userId:String
    ):User  //returns a user object
}