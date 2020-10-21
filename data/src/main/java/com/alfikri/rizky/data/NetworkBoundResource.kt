package com.alfikri.rizky.data

import com.alfikri.rizky.data.source.remote.network.ApiResponse
import com.alfikri.rizky.domain.Resource
import kotlinx.coroutines.flow.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version NetworkBoundResource, v 0.1 9/21/2020 12:27 PM by Rizky Alfikri Rachmat
 */
abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        var dbSource: ResultType? = null
        if (needDataFromDB()) {
            dbSource = loadFromDB().first()
        }
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emit(Resource.Success(fetchDataFromCall(apiResponse.data)))
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { resultData ->
                        Resource.Success(resultData, apiResponse.errorMessage)
                    })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Failed<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { resultData ->
                (Resource.Success(resultData))
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun needDataFromDB(): Boolean

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun fetchDataFromCall(data: RequestType): ResultType

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result

}