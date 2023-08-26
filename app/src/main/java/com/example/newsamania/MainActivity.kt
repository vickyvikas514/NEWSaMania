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
    var pageNum :Int = 1
    var totalresult = -1
    val TAG = "vickyvikas515"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        val newslist = findViewById<RecyclerView>(R.id.newslist)
        newslist.adapter = adapter
       // newslist.layoutManager = LinearLayoutManager(this@MainActivity)
        val layoutManager = StackLayoutManager(false,true,1.5f)
        newslist.layoutManager = layoutManager
        layoutManager.setItemChangedListener(object:StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                Log.d(TAG,"First Visible Item - ${layoutManager.findFirstVisibleItemPosition()}")
                Log.d(TAG,"Total Count ${layoutManager.itemCount}")
            }
        })







        getNews()

    }
    private fun getNews(){
        val news = NewsService.newsInctance.getHeadline("in",pageNum)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news != null){
                    Log.d("vickyvikas514",news.toString())
                    totalresult = news.totalResults
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