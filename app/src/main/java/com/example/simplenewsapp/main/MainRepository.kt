package com.example.simplenewsapp.main

import com.example.simplenewsapp.data.local.ArticleDatabase
import com.example.simplenewsapp.data.local.models.Article
import com.example.simplenewsapp.data.remote.RetrofitInstance

class MainRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery:String, pageNumber:Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getAllArticleDao().upsert(article)

    fun getSavedNews() = db.getAllArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getAllArticleDao().delete(article)

}