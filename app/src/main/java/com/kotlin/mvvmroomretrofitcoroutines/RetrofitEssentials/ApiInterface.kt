package com.kotlin.recyclerviewcoroutines.RetrofitEssentials

import com.kotlin.recyclerviewcoroutines.Models.QuotesModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("quotes?page=1")
    suspend fun getQuotes(
        @Query("page") page: Int
    ): Response<QuotesModel>

}