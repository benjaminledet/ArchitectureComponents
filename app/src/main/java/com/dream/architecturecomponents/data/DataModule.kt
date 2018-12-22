package com.dream.architecturecomponents.data

import androidx.room.Room
import com.dream.architecturecomponents.data.locale.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {

    single { Room.databaseBuilder(androidApplication(), MovieDatabase::class.java, MovieDatabase.DATABASE_NAME).build() }

    single { get<MovieDatabase>().movieDao() }

    single { MovieRepository() }

}