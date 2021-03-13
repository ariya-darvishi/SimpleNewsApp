package com.example.simplenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.main.MainRepository
import com.example.simplenewsapp.main.MainViewModel
import com.example.simplenewsapp.main.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

//    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

//        val newsRepository = MainRepository(ArticleDatabase(this))
//        val mainViewModelProviderFactory = MainViewModelProviderFactory(newsRepository)
//        viewModel =
//            ViewModelProvider(this, mainViewModelProviderFactory).get(MainViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}