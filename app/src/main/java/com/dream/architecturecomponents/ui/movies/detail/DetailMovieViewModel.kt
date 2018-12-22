package com.dream.architecturecomponents.ui.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.data.MovieRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DetailMovieViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    var movieId: MutableLiveData<Int> = MutableLiveData()

    var movie: LiveData<Movie> = Transformations.switchMap(movieId) { id -> movieRepository.getById(id) }
}