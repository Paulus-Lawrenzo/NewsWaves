package com.android.newswaves.repository

import com.android.newswaves.api.RetrofitInstance
import com.android.newswaves.db.ArticleDatabase
import com.android.newswaves.model.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}