package com.example.moviecatalogue.core.data.source

import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.repository.MovieRepo
import com.example.moviecatalogue.core.data.source.local.LocalDataSource
import com.example.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.example.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.example.moviecatalogue.core.data.source.vo.Resource
import com.example.moviecatalogue.core.utils.AppExecutors
import com.example.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepo {


    override fun getMovie(): Flow<Resource<List<Movie>>> =
        object : com.example.moviecatalogue.core.data.source.NetworkBoundResource<List<Movie>, List<MovieDetailResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getDataMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetailResponse>>> =
                remoteDataSource.getListMovie()

            override suspend fun saveCallResult(data: List<MovieDetailResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getMovieFav(): Flow<List<Movie>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setMovieFav(movie: Movie, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.updateFavMovie(moviesEntity, state)
        }
    }


}