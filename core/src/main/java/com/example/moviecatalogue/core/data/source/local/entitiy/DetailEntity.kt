package com.example.moviecatalogue.core.data.source.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "detail_entity")
data class DetailEntity(
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "release_date")
    val date: String,
    @ColumnInfo(name = "genres")
    val genres: List<String>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String
)