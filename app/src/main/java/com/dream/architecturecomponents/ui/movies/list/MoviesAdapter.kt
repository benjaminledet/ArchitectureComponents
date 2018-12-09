package com.dream.architecturecomponents.ui.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dream.architecturecomponents.BR
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.locale.Movie

class MoviesAdapter: ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    var onClick: ((item: Movie) -> Unit)? = null
    var onLongClick: ((item: Movie) -> Unit)? = null

    override fun getItemViewType(position: Int): Int = R.layout.item_movie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return MovieViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), object: OnMovieClickListener {
            override fun onItemClick(movie: Movie) {
                onClick?.invoke(movie)
            }

            override fun onItemLongClick(movie: Movie): Boolean {
                onLongClick?.invoke(movie)
                return true
            }
        })
    }

    class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    }

    class MovieViewHolder(private val viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(movie: Movie, onMovieClickListener: OnMovieClickListener) {
            viewDataBinding.setVariable(BR.movie, movie)
            viewDataBinding.setVariable(BR.listener, onMovieClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    interface OnMovieClickListener {

        fun onItemClick(movie: Movie)

        fun onItemLongClick(movie: Movie): Boolean

    }
}