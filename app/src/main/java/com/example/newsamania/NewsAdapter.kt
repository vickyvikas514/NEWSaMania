package com.example.newsamania

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,val articles : List<Article>):Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View): ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.NewsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.NewsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.Description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
    }
}