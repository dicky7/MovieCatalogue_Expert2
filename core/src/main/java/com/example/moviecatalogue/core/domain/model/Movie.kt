package com.example.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val date: String?,
    val vote_average: Double,
    val fav: Boolean
): Parcelable