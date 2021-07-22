package com.example.moviecatalogue.core.data.source.remote.api

import com.example.moviecatalogue.core.BuildConfig
import com.example.moviecatalogue.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface RetrofitEndPoint {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getMovies(): MovieResponse
}