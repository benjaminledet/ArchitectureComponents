package com.dream.architecturecomponents.ui

import com.dream.architecturecomponents.ui.movies.create.CreateMovieViewModel
import com.dream.architecturecomponents.ui.movies.detail.DetailMovieViewModel
import com.dream.architecturecomponents.ui.movies.list.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModule = module {

    viewModel { MoviesViewModel(androidApplication()) }

    viewModel { CreateMovieViewModel(androidApplication()) }

    viewModel { DetailMovieViewModel(androidApplication()) }

}