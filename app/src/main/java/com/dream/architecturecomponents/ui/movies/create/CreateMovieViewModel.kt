package com.dream.architecturecomponents.ui.movies.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository
import java.util.*

class CreateMovieViewModel(application: Application): AndroidViewModel(application) {

    var title: String = ""

    var overview: String = ""

    var releaseDate: Date = Date()

    var isForAdultsOnly: Boolean = false

    fun insert() {
        MovieRepository.insert(Movie(title = title, overview = overview, releaseDate = releaseDate, isForAdultsOnly = isForAdultsOnly))
    }
}