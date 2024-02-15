package com.android.newswaves.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.newswaves.R
import com.android.newswaves.model.Article
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            val itemArticle : View = findViewById(R.id.itemArticle)
            val ivArticleImage: ImageView = findViewById(R.id.ivArticleImage)
            val tvSource: TextView = findViewById(R.id.tvSource)
            val tvTitle: TextView = findViewById(R.id.tvTitle)
            val tvDescription: TextView = findViewById(R.id.tvDescription)
            val tvPublishedAt: TextView = findViewById(R.id.tvPublishedAt)

            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            itemArticle.setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun addItem(article: Article, position: Int) {
        val curList = differ.currentList.toMutableList()
        curList.add(position, article)
        differ.submitList(curList)
        notifyDataSetChanged()
    }

    fun deleteArticle(article: Article) {
        val curList = differ.currentList.toMutableList()
        curList.remove(article)
        differ.submitList(curList)
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}