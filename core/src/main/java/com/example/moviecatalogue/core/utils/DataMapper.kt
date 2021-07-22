package com.example.moviecatalogue.core.utils

import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.data.source.local.entitiy.MovieEntity
import com.example.moviecatalogue.core.data.source.remote.response.MovieDetailResponse

object DataMapper {
    fun mapResponseToEntities(list: List<MovieDetailResponse>): List<MovieEntity> {
        val mList = ArrayList<MovieEntity>()
        list.map {
            val movie = MovieEntity(
                id = it.id,
                coverMovie = it.posterPath,
                title = it.title,
                date = it.date,
                vote_average = it.vote_average,
                desc = it.overview,
                isFav = false
            )
            mList.add(movie)
        }
        return mList
    }

    fun mapEntitiesToDomain(list: List<MovieEntity>): List<Movie> =
        list.map {
            Movie(
                id = it.id,
                posterPath = it.coverMovie,
                title = it.title,
                date =it.date,
                vote_average = it.vote_average,
                overview = it.desc,
                fav = it.isFav
            )
        }

    fun mapDomainToEntity(movie: Movie) = MovieEntity(
        id = movie.id,
        coverMovie = movie.posterPath,
        title = movie.title,
        date = movie.date,
        vote_average = movie.vote_average,
        desc = movie.overview,
        isFav = movie.fav
    )
}