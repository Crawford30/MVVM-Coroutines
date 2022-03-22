package com.example.mvvm_courotines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {
    const val BASE_URL = "https://open-api.xyz/"

    //Singleton retrofit Builder
    val retrofitBuilder:Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //we need a json convertor because of the response in Gson, Converts Gson to java objects
    }


    //Singleton api service
    val apiService:ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }


}