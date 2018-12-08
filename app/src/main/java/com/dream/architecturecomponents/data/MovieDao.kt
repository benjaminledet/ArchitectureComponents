package com.dream.architecturecomponents.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getById(id: Int): LiveData<Movie>

    @Query("SELECT * FROM Movie ORDER BY title")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie ORDER BY title")
    fun getAllLive(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Movie>)

    @Delete
    fun delete(movie: Movie)

}