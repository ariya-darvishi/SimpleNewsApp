package com.example.simplenewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.simplenewsapp.NewsActivity
import com.example.simplenewsapp.R
import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.main.MainRepository
import com.example.simplenewsapp.main.MainViewModel
import com.example.simplenewsapp.main.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: MainViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel
        val newsRepository = MainRepository(ArticleDatabase(requireContext()))
        val mainViewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, mainViewModelProviderFactory).get(MainViewModel::class.java)


        val article = args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }
}