package com.dream.architecturecomponents.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("budget")
    val budget: Long,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("vote_average")
    val voteAverage: Double
)