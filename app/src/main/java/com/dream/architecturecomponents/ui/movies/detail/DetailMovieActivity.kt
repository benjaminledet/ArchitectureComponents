package com.dream.architecturecomponents.ui.movies.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository
import com.dream.architecturecomponents.extension.dateToString
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMovieActivity : AppCompatActivity() {

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        doAsync {
            movie = MovieRepository.getById(intent.getIntExtra("id", 0) )
            uiThread {
                setupToolbar()
                setupViews()
            }
        }
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
        setSupportActionBar(toolbar)
        title = movie?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        movie?.let { movie ->
            overview.text = movie.overview

            releaseDate.text = movie.releaseDate.dateToString().capitalize()

            isForAdultsOnly.text = getString(if (movie.isForAdultsOnly) R.string.yes else R.string.no)
        }
    }
}
