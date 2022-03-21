package com.kotlin.recyclerviewcoroutines.Models

data class QuotesModel(
    val count: Long,
    val totalCount: Long,
    val page: Long,
    val totalPages: Long,
    val lastItemIndex: Long,
    val results: List<Result>
)
