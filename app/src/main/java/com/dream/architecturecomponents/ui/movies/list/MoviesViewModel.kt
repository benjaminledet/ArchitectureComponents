package com.dream.architecturecomponents.ui.movies.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository

class MoviesViewModel(application: Application): AndroidViewModel(application) {

    var movies: LiveData<List<Movie>> = MovieRepository.getAll()

    fun delete(movie: Movie) {
        MovieRepository.delete(movie)
    }
}