package com.dream.architecturecomponents.data

import java.util.*

data class Movie(

    var id: Int = MovieRepository.movies.size + 1,

    var title: String = "Sans titre",

    var overview: String = "Inconnu",

    var releaseDate: Date = Date(),

    var isForAdultsOnly: Boolean = false

)