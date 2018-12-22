package com.dream.architecturecomponents.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.dream.architecturecomponents.data.locale.MovieDao
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.data.remote.MovieResponse
import com.dream.architecturecomponents.data.remote.MovieService
import com.dream.architecturecomponents.data.remote.MoviesResponseCallback
import com.dream.architecturecomponents.extension.toDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class MovieRepository: KoinComponent {

    private val movieDao: MovieDao by inject()

    private val service = MovieService.create()

    //region locale

    fun insertAll(movies: List<Movie>) = doAsync {
        movieDao.insertAll(movies)
        Log.d("movieRepository","inserting movies: $movies")
    }

    fun insert(movie: Movie) =
        insertAll(listOf(movie))

    fun delete(movie: Movie) = doAsync { movieDao.delete(movie) }

    fun getById(id: Int): LiveData<Movie> = movieDao.getById(id)

    fun getAll(): LiveData<List<Movie>> = movieDao.getAllLive()

    //endregion

    //region remote

    fun downloadMovies(callback: MoviesResponseCallback) {
        service.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { moviesListResponse -> insertAll(moviesListResponse.results.map { movieResponse -> movieResponseToMovie(movieResponse) }) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    fun downloadMoviesWithExtraInfos(callback: MoviesResponseCallback) {
        service.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .flatMap { moviesListResponse -> Observable.fromIterable(moviesListResponse.results) }
            .flatMap { movieShortResponse -> service.getMovie(movieShortResponse.id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { movieFullResponse -> insert(movieResponseToMovie(movieFullResponse)) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    private fun movieResponseToMovie(movieResponse: MovieResponse): Movie =
        Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            coverUrl = movieResponse.backdropPath,
            overview = movieResponse.overview,
            releaseDate = movieResponse.releaseDate.toDate("yyyy-MM-dd") ?: Date(),
            isForAdultsOnly = movieResponse.adult
        )

    //endregion
}