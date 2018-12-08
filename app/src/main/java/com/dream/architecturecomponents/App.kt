package com.dream.architecturecomponents

import android.app.Application
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository
import org.jetbrains.anko.doAsync
import java.util.*

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieRepository.initialize(this)

        val calendar = Calendar.getInstance()

        val movies = listOf(
            Movie(id = 1, title = "Avengers", overview = getString(R.string.avengers_overview), releaseDate = calendar.apply { set(2012, 3, 25) }.time, isForAdultsOnly = false),
            Movie(id = 2, title = "Jumanji", overview = getString(R.string.jumanji_overview), releaseDate = calendar.apply { set(2017, 11, 20) }.time, isForAdultsOnly = false),
            Movie(id = 3, title = "Conjuring: Les dossiers Warren", overview = getString(R.string.conjuring_overview), releaseDate = calendar.apply { set(2013, 7, 21) }.time, isForAdultsOnly = true)
        )

        doAsync { MovieRepository.insertAll(movies) }
    }
}