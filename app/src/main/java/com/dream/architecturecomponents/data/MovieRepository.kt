package com.dream.architecturecomponents.data

import android.app.Application
import android.util.Log

object MovieRepository {

    private lateinit var database: MovieDatabase

    private lateinit var movieDao: MovieDao

    fun initialize(application: Application) {
        database = MovieDatabase.buildInstance(application)
        movieDao = database.movieDao()
    }

    fun insertAll(movies: List<Movie>) {
        movies.forEach { movie -> if(movie.id == 0) movie.id = (getAll().maxBy { it.id }?.id ?: 0) + 1 }
        movieDao.insertAll(movies)
        Log.d("movieRepository","inserting movies: $movies")
    }

    fun insert(movie: Movie) = insertAll(listOf(movie))

    fun delete(movie: Movie) {
        movieDao.delete(movie)
    }

    fun getById(id: Int): Movie = movieDao.getById(id)

    fun getAll(): List<Movie> = movieDao.getAll()
}