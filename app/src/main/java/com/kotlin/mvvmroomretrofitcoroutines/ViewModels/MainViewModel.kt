package com.kotlin.mvvmdemo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvmroomretrofitcoroutines.Repository.QuoteRepository
import com.kotlin.mvvmroomretrofitcoroutines.Repository.Response
import com.kotlin.recyclerviewcoroutines.Models.QuotesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<Response<QuotesModel>>
        get() = repository.quotes
}