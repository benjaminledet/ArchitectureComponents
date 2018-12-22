package com.dream.architecturecomponents.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dream.architecturecomponents.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "MovieDatabase"
    }
}