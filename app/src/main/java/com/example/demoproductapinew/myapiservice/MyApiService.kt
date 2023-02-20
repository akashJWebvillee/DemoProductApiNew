package com.example.demoproductapinew.myapiservice

import com.example.demoproductapinew.response.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyApiService {

    @GET("2f06b453-8375-43cf-861a-06e95a951328")
    fun getMyProductsData(): Call<ProductsResponse?>?

}