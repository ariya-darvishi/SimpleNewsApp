package com.example.simplenewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simplenewsapp.NewsActivity
import com.example.simplenewsapp.R
import com.example.simplenewsapp.main.MainViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

//    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel
    }
}