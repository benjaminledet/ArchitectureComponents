package com.dream.architecturecomponents.ui.movies.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.data.remote.MoviesResponseCallback
import com.dream.architecturecomponents.databinding.ActivityMoviesBinding
import com.dream.architecturecomponents.extension.showAction
import com.dream.architecturecomponents.extension.showError
import com.dream.architecturecomponents.extension.startAnimatedActivity
import com.dream.architecturecomponents.ui.base.BaseActivity
import com.dream.architecturecomponents.ui.movies.create.CreateMovieActivity
import com.dream.architecturecomponents.ui.movies.detail.DetailMovieActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : BaseActivity<MoviesViewModel, ActivityMoviesBinding>() {

    override val layout: Int = R.layout.activity_movies

    override val viewModel: MoviesViewModel by viewModel()

    private var moviesAdapter = MoviesAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        setupAdapter()
        setupFab()
        setupRecyclerView()
        setupSwipeRefreshLayout()
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

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.apply {

            fun refresh() {
                isRefreshing = true
                viewModel.refresh(object: MoviesResponseCallback {
                    override fun onSuccess() {
                        binding.root.showAction(getString(R.string.movies_loaded))
                        isRefreshing = false
                    }

                    override fun onError(throwable: Throwable) {
                        binding.root.showError(getString(R.string.movies_loading_error))
                        isRefreshing = false
                    }
                })
            }

            setOnRefreshListener { refresh() }
            post { refresh() }
        }
    }

    private fun showDeletePopup(movie: Movie) {
        alert(getString(R.string.delete_movie_warning, movie.title)) {
            yesButton { viewModel.delete(movie) }
            noButton { }
        }.show()
    }
}
