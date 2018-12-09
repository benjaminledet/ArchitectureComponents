package com.dream.architecturecomponents.ui.movies.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.databinding.ActivityMoviesBinding
import com.dream.architecturecomponents.extension.startAnimatedActivity
import com.dream.architecturecomponents.ui.movies.create.CreateMovieActivity
import com.dream.architecturecomponents.ui.movies.detail.DetailMovieActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val viewModel: MoviesViewModel by lazy { ViewModelProviders.of(this).get(MoviesViewModel::class.java) }

    private var moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(this)

        setupAdapter()
        setupFab()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        viewModel.movies.observe(this, Observer {
            moviesAdapter.submitList(it)
        })

        moviesAdapter.apply {
            onClick = { startAnimatedActivity(intentFor<DetailMovieActivity>("id" to it.id)) }
            onLongClick = { showDeletePopup(it) }
        }
    }

    private fun setupFab() {
        binding.fab.onClick { startAnimatedActivity(intentFor<CreateMovieActivity>()) }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@MoviesActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            adapter = moviesAdapter
        }
    }

    private fun showDeletePopup(movie: Movie) {
        alert(getString(R.string.delete_movie_warning, movie.title)) {
            yesButton { viewModel.delete(movie) }
            noButton { }
        }.show()
    }
}
