package com.kotlin.mvvmroomretrofitcoroutines.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kotlin.mvvmroomretrofitcoroutines.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

//This File is made to run in background so that it can fetch new data from random generated page number we created
class QuoteWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {

    override fun doWork(): Result {

        Log.d("Worker", "Running Now.. ${Date()}")

        val repository = (context as QuoteApplication).repository
        CoroutineScope(Dispatchers.IO).launch {
            repository.runInBackground()
        }
        return Result.success()
    }

}