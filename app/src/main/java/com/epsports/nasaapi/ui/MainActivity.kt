package com.epsports.nasaapi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.epsports.nasaapi.databinding.ActivityMainBinding
import com.epsports.nasaapi.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerListener()

    }

    @SuppressLint("SetTextI18n")
    private fun observerListener() {
        viewModel.pictureOfTheDay.observe(this) {
            try {
                it.let {
                    binding.apply {
                        tvDate.text = "Date: ${it.date.toString()}"
                        tvTitle.text = "Title: ${it.title.toString()}"
                        tvExplanation.text = "Explanation: ${it.explanation.toString()}"
                        btnWatch.setOnClickListener { view ->
                            when (it.mediaType) {
                                "image" -> {
                                    binding.ivWatchImage.load(it.url)
                                }
                                else -> {
                                    val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                                    intent.putExtra("url", it.url)
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}