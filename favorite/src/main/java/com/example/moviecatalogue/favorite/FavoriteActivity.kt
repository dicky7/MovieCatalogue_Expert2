package com.example.moviecatalogue.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.core.ui.MovieAdapter
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.detail.DetailActivity
import com.example.moviecatalogue.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private  val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }


        movieAdapter.onItemClicked = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                startActivity(it)
            }
        }

        viewModel.liveData.observe(this, {
            movieAdapter.setData(it)
            binding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        recyclerViewr()

    }

    private fun recyclerViewr(){
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}