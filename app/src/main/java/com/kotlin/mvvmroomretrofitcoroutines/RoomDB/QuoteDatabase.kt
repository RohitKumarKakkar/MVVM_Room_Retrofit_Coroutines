package com.kotlin.mvvmroomretrofitcoroutines.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.recyclerviewcoroutines.Models.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {  // To Create DB in Background Thread , But Only Once
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext, QuoteDatabase::class.java,
                            "quoteDB"
                        ).build()
                }
            }
            return INSTANCE!!
        }
    }
}