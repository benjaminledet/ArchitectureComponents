package com.dream.architecturecomponents.ui.movies.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.databinding.ActivityCreateMovieBinding
import com.dream.architecturecomponents.ui.base.BaseActivity
import com.dream.architecturecomponents.ui.movies.detail.DetailMovieViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CreateMovieActivity : BaseActivity<CreateMovieViewModel, ActivityCreateMovieBinding>() {

    override val layout: Int = R.layout.activity_create_movie

    override val viewModel: CreateMovieViewModel by viewModel()

    private var datePickerDialog: DatePickerDialog? = null

    override fun initView(savedInstanceState: Bundle?) {
        setupDatePicker()
        setupToolbar()
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.confirm -> {
            viewModel.insert()
            ActivityCompat.finishAfterTransition(this@CreateMovieActivity)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun setupViews() {
        binding.titleEditText.requestFocus()

        binding.releaseDateEditText.onClick { datePickerDialog?.show() }
    }

    private fun setupDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = viewModel.releaseDate.value ?: Date()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                viewModel.releaseDate.value = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}
