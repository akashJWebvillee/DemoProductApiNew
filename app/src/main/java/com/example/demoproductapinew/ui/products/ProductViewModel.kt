package com.example.demoproductapinew.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproductapinew.myapiservice.MyApiService
import com.example.demoproductapinew.response.ProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {

    private val myApiService: MyApiService

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myApiService = retrofit.create(MyApiService::class.java)

    }

    fun getMyProductsData(): LiveData<ProductsResponse> {

        val data = MutableLiveData<ProductsResponse>()

        myApiService.getMyProductsData()?.enqueue(object : Callback<ProductsResponse?> {

            override fun onResponse(
                call: Call<ProductsResponse?>,
                response: Response<ProductsResponse?>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    // handle error
                }
            }

            override fun onFailure(call: Call<ProductsResponse?>, t: Throwable) {

            }
        })

        return data

    }


}