package com.dream.architecturecomponents.ui.movies.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import com.dream.architecturecomponents.data.MovieRepository
import com.dream.architecturecomponents.extension.dateToString
import kotlinx.android.synthetic.main.activity_create_movie.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import org.jetbrains.anko.uiThread
import java.util.*

class CreateMovieActivity : AppCompatActivity() {

    private var movie = Movie()

    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_movie)

        setupDatePicker()
        setupToolbar()
        setupViews()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
        R.id.confirm -> {
            MovieRepository.insert(movie)
            ActivityCompat.finishAfterTransition(this@CreateMovieActivity)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun setupViews() {
        titleEditText.apply {
            requestFocus()
            textChangedListener { onTextChanged { charSequence, _, _, _ -> movie.title = charSequence.toString().capitalize() } }
        }

        overviewEditText.textChangedListener { onTextChanged { charSequence, _, _, _ -> movie.overview = charSequence.toString().capitalize() } }

        releaseDateEditText.onClick { datePickerDialog?.show() }

        isForAdultsOnlySwitch.onCheckedChange { _, isChecked -> movie.isForAdultsOnly = isChecked }
    }

    private fun setupDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = movie.releaseDate

        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                releaseDateEditText.setText(calendar.time.dateToString().capitalize())
                movie.releaseDate = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}
