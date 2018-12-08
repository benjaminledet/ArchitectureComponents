package com.dream.architecturecomponents.ui.movies.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var onClick: ((item: Movie) -> Unit)? = null
    var onLongClick: ((item: Movie) -> Unit)? = null

    private var data = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position], object:
            OnMovieClickListener {
            override fun onItemClick(movie: Movie) {
                onClick?.invoke(movie)
            }

            override fun onItemLongClick(movie: Movie) {
                onLongClick?.invoke(movie)
            }
        })
    }

    fun replaceData(newData: List<Movie>) {
        this.data = newData
        notifyDataSetChanged()
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