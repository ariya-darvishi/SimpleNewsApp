package com.example.simplenewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenewsapp.NewsActivity
import com.example.simplenewsapp.R
import com.example.simplenewsapp.adapter.NewsRecyclerViewAdapter
import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.main.MainRepository
import com.example.simplenewsapp.main.MainViewModel
import com.example.simplenewsapp.main.MainViewModelProviderFactory
import com.example.simplenewsapp.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.simplenewsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: MainViewModel
    lateinit var newsAdapter: NewsRecyclerViewAdapter

    val TAG = "SearchNews Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel

        val newsRepository = MainRepository(ArticleDatabase(requireContext()))
        val mainViewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, mainViewModelProviderFactory).get(MainViewModel::class.java)

        setupRecyclerView()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.data?.let { message ->
                        Log.e(TAG, "An Error occured: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsRecyclerViewAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}