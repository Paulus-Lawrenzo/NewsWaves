package com.android.newswaves.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.android.newswaves.R
import com.android.newswaves.ui.activity.NewsActivity
import com.android.newswaves.ui.viewmodel.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        val webView: WebView = view.findViewById(R.id.webView)
        webView.apply {
            webView.webViewClient = WebViewClient()
            article.url?.let { webView.loadUrl(it) }
        }
    }
}