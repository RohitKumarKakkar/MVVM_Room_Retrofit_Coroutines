package com.kotlin.mvvmroomretrofitcoroutines.Repository

import com.kotlin.recyclerviewcoroutines.Models.QuotesModel

//this class is made to handle the worker request or api request.
/*
    Below code is commented because we can give this responses some flexibility by not defining the model name
    and defining <T> as a generic to give more flexible and use this code at multiple times and places
*/

/*sealed class Response2(val data: QuotesModel? = null, val errorMessage: String? = null) {
    class Loading : Response2()
    class Success(quoteModel: QuotesModel) : Response2(data = quoteModel)
    class Error(errorMessage: String) : Response2(errorMessage = errorMessage)
}*/

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
