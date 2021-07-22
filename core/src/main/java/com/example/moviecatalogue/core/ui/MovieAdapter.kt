package com.example.moviecatalogue.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.core.BuildConfig
import com.example.moviecatalogue.core.R
import com.example.moviecatalogue.core.databinding.ItemListMovieBinding
import com.example.moviecatalogue.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list = ArrayList<Movie>()
    var onItemClicked: ((Movie) -> Unit)? = null

    fun setData(data: List<Movie>?) {
        if (data == null) return
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                movie.apply {
                    listItemTitle.text = title
                    listItemRelease.text = date

                    Glide.with(itemView.context)
                        .load(BuildConfig.IMAGE_URL + posterPath)
                        .transform(RoundedCorners(20))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(listItemImg)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(list[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}