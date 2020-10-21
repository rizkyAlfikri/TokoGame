package com.alfikri.rizky.domain


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version Resource, v 0.1 9/21/2020 12:28 PM by Rizky Alfikri Rachmat
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T, message: String = "") : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Failed<T>(message: String, data: T? = null) : Resource<T>(data, message)
}