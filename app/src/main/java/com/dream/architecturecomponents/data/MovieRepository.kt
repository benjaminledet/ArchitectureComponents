package com.dream.architecturecomponents.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

object MovieRepository {

    private lateinit var database: MovieDatabase

    private lateinit var movieDao: MovieDao

    fun initialize(application: Application) {
        database = MovieDatabase.buildInstance(application)
        movieDao = database.movieDao()
    }

    fun insertAll(movies: List<Movie>) = doAsync {
        movies.forEach { movie -> if(movie.id == 0) movie.id = (movieDao.getAll().maxBy { it.id }?.id ?: 0) + 1 }
        movieDao.insertAll(movies)
        Log.d("movieRepository","inserting movies: $movies")
    }

    fun insert(movie: Movie) = insertAll(listOf(movie))

    fun delete(movie: Movie) = doAsync { movieDao.delete(movie) }

    fun getById(id: Int): LiveData<Movie> = movieDao.getById(id)

    fun getAll(): LiveData<List<Movie>> = movieDao.getAllLive()
}