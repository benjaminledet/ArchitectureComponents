package com.dream.architecturecomponents.ui.movies.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val viewModel: DetailMovieViewModel by lazy { ViewModelProviders.of(this).get(DetailMovieViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(this)

        viewModel.movieId.value = intent.getIntExtra("id", 0)

        setupToolbar()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
