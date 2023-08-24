package com.example.newsamania

import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import library.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        val newslist = findViewById<RecyclerView>(R.id.newslist)
        newslist.adapter = adapter
       // newslist.layoutManager = LinearLayoutManager(this@MainActivity)
        newslist.layoutManager = StackLayoutManager(false,true,0.5f)


        getNews()
    }
    private fun getNews(){
        val news = NewsService.newsInctance.getHeadline("in",2)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news != null){
                    Log.d("vickyvikas514",news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("vickyvikas514","Error in fetching News",t)

            }
        })
    }
}