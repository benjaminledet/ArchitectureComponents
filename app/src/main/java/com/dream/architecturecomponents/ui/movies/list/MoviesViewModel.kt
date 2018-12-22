package com.dream.architecturecomponents.ui.movies.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.data.MovieRepository
import com.dream.architecturecomponents.data.remote.MoviesResponseCallback
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MoviesViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    var movies: LiveData<List<Movie>> = movieRepository.getAll()

    fun delete(movie: Movie) {
        movieRepository.delete(movie)
    }

    fun refresh(callback: MoviesResponseCallback) {
        movieRepository.downloadMovies(callback)
    }
}