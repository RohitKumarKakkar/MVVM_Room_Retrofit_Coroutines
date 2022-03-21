package com.kotlin.mvvmroomretrofitcoroutines.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.mvvmroomretrofitcoroutines.RoomDB.QuoteDatabase
import com.kotlin.mvvmroomretrofitcoroutines.Utils.NetworkUtils
import com.kotlin.recyclerviewcoroutines.Models.QuotesModel
import com.kotlin.recyclerviewcoroutines.RetrofitEssentials.ApiInterface

class QuoteRepository(
    private val apiInterface: ApiInterface,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuotesModel>>() //can be modified

    val quotes: LiveData<Response<QuotesModel>> // get value from mutable live data and this can not be modified
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) { // Runs if internet available

            try {
                val result = apiInterface.getQuotes(page) // Data stored in result variable
                if (result?.body() != null) {
                    //Storing data to Room DB
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body())) //push values of result in mutable livedata
                } else {
                    quotesLiveData.postValue(Response.Error("Api Error"))
                }

            } catch (e: Exception) {
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else { // Runs if internet not available
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuotesModel(1, 1, 1, 1, 1, quotes)
            quotesLiveData.postValue(Response.Success(quoteList))
        }
    }

    suspend fun runInBackground() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = apiInterface.getQuotes(randomNumber)
        if (result?.body() != null) {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }
    }

}