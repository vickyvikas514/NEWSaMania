package com.example.newsamania

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Helper {
    private lateinit var newsAdapter: NewsAdapter
    private val apiKey = "927db541dc8048cb97f40543d6c05a52"  // enter your api key
    private val country = "us"
    private lateinit var call: Call<NewsResponse>
    fun fetchNews(category: String, context: Context, swipeRefreshLayout : SwipeRefreshLayout, recyclerView : RecyclerView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApiService = retrofit.create(ApiInterface::class.java)
        if(category=="no"){
            call = newsApiService.getTopHeadlines(country, apiKey)
        }
        else{
            call = newsApiService.getCategoryNews(country, category, apiKey)
        }
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    var articles = newsResponse?.articles
                    articles?.let {
                        articles = it.shuffled() // Shuffle the list of articles
                        newsAdapter = NewsAdapter(articles!!)
                        recyclerView.adapter = newsAdapter
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(context, "Data is not fetched successfully", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
