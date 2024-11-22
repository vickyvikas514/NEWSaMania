package com.example.newsamania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: com.example.newsamania.databinding.ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.newsamania.databinding.ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.loader).into(binding.loadingGif)
        delayTimer()
    }
    private fun delayTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToNext()
        },3000)
    }
    private fun goToNext() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
    }
}