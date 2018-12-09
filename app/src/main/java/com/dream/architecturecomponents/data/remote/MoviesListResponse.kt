package com.dream.architecturecomponents.data.remote

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResponse>,

    @SerializedName("total_results")
    var totalResults: Int,

    @SerializedName("total_pages")
    var totalPages: Int
)