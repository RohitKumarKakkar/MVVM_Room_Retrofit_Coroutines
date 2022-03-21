package com.kotlin.mvvmroomretrofitcoroutines.RoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.recyclerviewcoroutines.Models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>
}