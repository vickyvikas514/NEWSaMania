package com.example.newsamania

import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }
    private fun getNews(){
        val news = NewsService.newsInctance.getHeadline("in",1)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news != null){
                    Log.d("vickyvikas514",news.toString())
                    adapter = NewsAdapter(this@MainActivity, news.articles)
                        val newslist = findViewById<RecyclerView>(R.id.newslist)
                    newslist.adapter = adapter
                    newslist.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("vickyvikas514","Error in fetching News",t)

            }
        })
    }
}