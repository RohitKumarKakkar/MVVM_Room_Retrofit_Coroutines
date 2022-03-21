package com.kotlin.mvvmroomretrofitcoroutines

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kotlin.mvvmroomretrofitcoroutines.Repository.QuoteRepository
import com.kotlin.mvvmroomretrofitcoroutines.RoomDB.QuoteDatabase
import com.kotlin.mvvmroomretrofitcoroutines.Worker.QuoteWorker
import com.kotlin.recyclerviewcoroutines.RetrofitEssentials.ApiInterface
import com.kotlin.recyclerviewcoroutines.RetrofitEssentials.RetrofitHelper
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() { //Setting Worker to run every one minute.
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 1, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {

        // getting instance of retrofit
        val call = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        //create room db object and calling it from singleton/saperate class
        val dao = QuoteDatabase.getDatabase(applicationContext)

        // passing retrofit instance to repository to get values
        repository = QuoteRepository(call, dao, applicationContext)
    }

}