package com.android.newswaves.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.newswaves.R

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}