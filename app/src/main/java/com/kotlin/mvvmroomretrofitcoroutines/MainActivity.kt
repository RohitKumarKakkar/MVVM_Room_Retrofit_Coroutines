package com.kotlin.mvvmroomretrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvmdemo.ViewModel.MainViewModel
import com.kotlin.mvvmdemo.ViewModel.MainViewModelFactory
import com.kotlin.mvvmroomretrofitcoroutines.Repository.QuoteRepository
import com.kotlin.mvvmroomretrofitcoroutines.Repository.Response
import com.kotlin.recyclerviewcoroutines.RetrofitEssentials.ApiInterface
import com.kotlin.recyclerviewcoroutines.RetrofitEssentials.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).repository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    Log.d("list: ", "Loading")
                    //Also can Show Loader on Screen
                }
                is Response.Success -> {
                    it.data?.let {
                        Log.d("list: ", it.results.toString())
                        Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                is Response.Error -> {
                    it.errorMessage?.let {
                        Log.d("list: ", "Some Error Occured")
                    }
                }
            }

        })

    }
}