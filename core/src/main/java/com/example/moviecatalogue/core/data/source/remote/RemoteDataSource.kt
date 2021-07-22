package com.example.moviecatalogue.core.data.source.remote

import android.util.Log
import com.example.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.example.moviecatalogue.core.data.source.remote.api.RetrofitEndPoint
import com.example.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val endPoint: RetrofitEndPoint) {

    suspend fun getListMovie(): Flow<ApiResponse<List<MovieDetailResponse>>> {
        return flow {
            try {
                val response = endPoint.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}