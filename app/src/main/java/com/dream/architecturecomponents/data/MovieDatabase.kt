package com.dream.architecturecomponents.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        fun buildInstance(context: Context) = Room
            .databaseBuilder(context, MovieDatabase::class.java, "MovieDatabase")
            .build()
    }
}