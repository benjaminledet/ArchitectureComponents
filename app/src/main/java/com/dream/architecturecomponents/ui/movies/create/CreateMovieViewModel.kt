package com.dream.architecturecomponents.ui.movies.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.data.MovieRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class CreateMovieViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    var title: MutableLiveData<String> = MutableLiveData()

    var overview: MutableLiveData<String> = MutableLiveData()

    var releaseDate: MutableLiveData<Date> = MutableLiveData()

    var isForAdultsOnly: MutableLiveData<Boolean> = MutableLiveData()

    fun insert() {
        movieRepository.insert(
            Movie(
                title = title.value?.capitalize() ?: "",
                overview = overview.value?.capitalize() ?: "",
                releaseDate = releaseDate.value ?: Date(),
                isForAdultsOnly = isForAdultsOnly.value ?: false
            )
        )
    }
}