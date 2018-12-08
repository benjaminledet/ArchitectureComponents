package com.dream.architecturecomponents.ui.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository

class DetailMovieViewModel(application: Application): AndroidViewModel(application) {

    var movieId: MutableLiveData<Int> = MutableLiveData()

    var movie: LiveData<Movie> = Transformations.switchMap(movieId) { id -> MovieRepository.getById(id) }
}