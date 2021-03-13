package com.example.simplenewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenewsapp.NewsActivity
import com.example.simplenewsapp.R
import com.example.simplenewsapp.adapter.NewsRecyclerViewAdapter
import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.main.MainRepository
import com.example.simplenewsapp.main.MainViewModel
import com.example.simplenewsapp.main.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_search_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: MainViewModel
    lateinit var newsAdapter: NewsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = MainRepository(ArticleDatabase(requireContext()))
        val mainViewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, mainViewModelProviderFactory).get(MainViewModel::class.java)
//        viewModel = (activity as NewsActivity).viewModel

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }



    }

    private fun setupRecyclerView() {
        newsAdapter = NewsRecyclerViewAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}