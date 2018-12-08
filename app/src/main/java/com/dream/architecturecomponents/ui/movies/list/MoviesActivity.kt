package com.dream.architecturecomponents.ui.movies.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository
import com.dream.architecturecomponents.extension.startAnimatedActivity
import com.dream.architecturecomponents.ui.movies.create.CreateMovieActivity
import com.dream.architecturecomponents.ui.movies.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.activity_movies.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MoviesActivity : AppCompatActivity() {

    private var moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setupAdapter()
        setupFab()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        MovieRepository.getAll().observe(this, Observer {
            moviesAdapter.submitList(it)
        })

        moviesAdapter.apply {
            onClick = { startAnimatedActivity(intentFor<DetailMovieActivity>("id" to it.id)) }
            onLongClick = { showDeletePopup(it) }
        }
    }

    private fun setupFab() {
        fab.onClick { startAnimatedActivity(intentFor<CreateMovieActivity>()) }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@MoviesActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            adapter = moviesAdapter
        }
    }

    private fun showDeletePopup(movie: Movie) {
        alert(getString(R.string.delete_movie_warning, movie.title)) {
            yesButton { MovieRepository.delete(movie) }
            noButton { }
        }.show()
    }
}
