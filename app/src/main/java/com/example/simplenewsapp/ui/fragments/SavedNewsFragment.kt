package com.example.simplenewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.simplenewsapp.NewsActivity
import com.example.simplenewsapp.R
import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.main.MainRepository
import com.example.simplenewsapp.main.MainViewModel
import com.example.simplenewsapp.main.MainViewModelProviderFactory

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = MainRepository(ArticleDatabase(requireContext()))
        val mainViewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, mainViewModelProviderFactory).get(MainViewModel::class.java)
//        viewModel = (activity as NewsActivity).viewModel

    }
}