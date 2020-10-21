package com.alfikri.rizky.data.source.remote.network


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version ApiResponse, v 0.1 9/21/2020 12:46 PM by Rizky Alfikri Rachmat
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    data class Empty(val errorMessage: String) : ApiResponse<Nothing>()
}