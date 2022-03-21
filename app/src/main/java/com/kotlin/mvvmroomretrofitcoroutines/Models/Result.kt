package com.kotlin.recyclerviewcoroutines.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteid: Int,
    val author: String,
    val content: String,
    val authorSlug: String,
    val length: Long,
    val dateAdded: String,
    val dateModified: String
)