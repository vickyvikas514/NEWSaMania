package com.example.newsamania

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "973d44ff048944ea8c6deb7e96ec327b"
interface NewsInterface{
@GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country: String,@Query("page") page:Int):Call<News>

}
object NewsService{
    val newsInctance:NewsInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInctance = retrofit.create(NewsInterface::class.java)
    }
}