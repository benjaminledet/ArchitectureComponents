package com.dream.architecturecomponents.ui.movies.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class MoviesAdapter: ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    var onClick: ((item: Movie) -> Unit)? = null
    var onLongClick: ((item: Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), object: OnMovieClickListener {
            override fun onItemClick(movie: Movie) {
                onClick?.invoke(movie)
            }

            override fun onItemLongClick(movie: Movie) {
                onLongClick?.invoke(movie)
            }
        })
    }

    class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, onMovieClickListener: OnMovieClickListener) {
            itemView.title.text = movie.title
            itemView.overview.text = movie.overview
            itemView.root.apply {
                onClick { onMovieClickListener.onItemClick(movie) }
                onLongClick { onMovieClickListener.onItemLongClick(movie) }
            }
        }
    }

    interface OnMovieClickListener {

        fun onItemClick(movie: Movie)

        fun onItemLongClick(movie: Movie)

    }
}