package com.example.moviecatalogue.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.core.data.source.vo.Resource
import com.example.moviecatalogue.core.ui.MovieAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val model: MovieViewModel by viewModel()
    private var activityMainBinding : ActivityMainBinding? = null
    private val binding get() = activityMainBinding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClicked = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                startActivity(it)
            }
        }
        populate()
    }

    private fun populate(){
        model.movies.observe(this, { movie->
            if (movie != null) {
                when (movie) {
                    is Resource.loading -> true.progressbar()
                    is Resource.success -> {
                        false.progressbar()
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.error -> {
                        false.progressbar()
                        binding.viewError.apply {
                            root.visibility = View.INVISIBLE
                            tvError.text = movie.message
                        }

                    }
                }
            }
        })

        setRecyclerView()

        val uri = Uri.parse("moviecatalogue://favorite")
        binding.fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setRecyclerView() {
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun Boolean.progressbar() {
        binding.progressBar.visibility = if (this) View.VISIBLE else View.INVISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}