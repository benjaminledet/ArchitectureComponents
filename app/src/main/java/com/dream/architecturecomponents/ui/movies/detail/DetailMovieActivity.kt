package com.dream.architecturecomponents.ui.movies.detail

import android.os.Bundle
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.databinding.ActivityDetailMovieBinding
import com.dream.architecturecomponents.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<DetailMovieViewModel, ActivityDetailMovieBinding>() {

    override val layout: Int = R.layout.activity_detail_movie

    override val viewModel: DetailMovieViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.movieId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
