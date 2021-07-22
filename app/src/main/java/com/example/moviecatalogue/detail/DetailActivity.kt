package com.example.moviecatalogue.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.R
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tolbar.setNavigationOnClickListener { onBackPressed() }

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        movieDetail(movie)
    }

    private fun movieDetail(movie: Movie?) {
        movie?.let {
            movie.apply {
                with(binding) {
                    titleDetail.text = it.title
                    releaseDetail.text = movie.date
                    overviewDetail.text = movie.overview

                    com.bumptech.glide.Glide.with(this@DetailActivity)
                        .load(BuildConfig.IMAGE_URL + movie.posterPath)
                        .into(binding.imgDetail)

                    var statFav = movie.fav
                    setFavoriteState(statFav)
                    btnAddFavorite.setOnClickListener {
                        statFav = !statFav
                        viewModel.setMovieFav(movie, statFav)
                        setFavoriteState(statFav)
                    }
                }
            }
        }
    }



    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.btnAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.btnAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}