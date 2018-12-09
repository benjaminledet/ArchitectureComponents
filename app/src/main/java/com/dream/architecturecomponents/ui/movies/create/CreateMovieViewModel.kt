package com.dream.architecturecomponents.ui.movies.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dream.architecturecomponents.data.locale.Movie
import com.dream.architecturecomponents.data.locale.MovieRepository
import java.util.*

class CreateMovieViewModel(application: Application): AndroidViewModel(application) {

    var title: MutableLiveData<String> = MutableLiveData()

    var overview: MutableLiveData<String> = MutableLiveData()

    var releaseDate: MutableLiveData<Date> = MutableLiveData()

    var isForAdultsOnly: MutableLiveData<Boolean> = MutableLiveData()

    fun insert() {
        MovieRepository.insert(
            Movie(
                title = title.value?.capitalize() ?: "",
                overview = overview.value?.capitalize() ?: "",
                releaseDate = releaseDate.value ?: Date(),
                isForAdultsOnly = isForAdultsOnly.value ?: false
            )
        )
    }
}