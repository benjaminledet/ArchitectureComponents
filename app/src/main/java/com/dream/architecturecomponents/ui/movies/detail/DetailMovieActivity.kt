package com.dream.architecturecomponents.ui.movies.detail

import android.os.Bundle
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.databinding.ActivityDetailMovieBinding
import com.dream.architecturecomponents.ui.base.BaseActivity

class DetailMovieActivity : BaseActivity<DetailMovieViewModel, ActivityDetailMovieBinding>() {

    override val layout: Int = R.layout.activity_detail_movie

    override fun setViewModel(): Class<DetailMovieViewModel> = DetailMovieViewModel::class.java

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.movieId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
