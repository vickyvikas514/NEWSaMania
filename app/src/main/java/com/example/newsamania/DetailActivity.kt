package com.example.newsamania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.graphics.green
import com.example.newsamania.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra("URL")
        if(url!=null){
            val detailwebView = findViewById<WebView>(R.id.detailWebView)
        detailwebView.settings.javaScriptEnabled = true
            detailwebView.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                  detailwebView.visibility = View.VISIBLE
                }
            }
            detailwebView.loadUrl(url)
        }
    }
}