package com.dream.architecturecomponents.ui.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.model.Movie
import com.dream.architecturecomponents.databinding.ItemMovieBinding
import com.dream.architecturecomponents.ui.base.BaseAdapter
import com.dream.architecturecomponents.ui.base.BaseViewHolder
import com.dream.architecturecomponents.utils.OnItemClickListener

class MoviesAdapter(lifecycleOwner: LifecycleOwner): BaseAdapter<Movie>(lifecycleOwner) {

    override fun layoutFor(position: Int): Int = R.layout.item_movie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie, *> {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return MovieViewHolder(binding)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding): BaseViewHolder<Movie, ItemMovieBinding>(binding) {

        override fun bind(lifecycleOwner: LifecycleOwner, item: Movie, listener: OnItemClickListener<Movie>) {
            super.bind(lifecycleOwner, item, listener)
            binding.title.text = item.title
        }
    }
}