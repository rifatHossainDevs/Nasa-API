package com.epsports.nasaapi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

    private fun observerListener() {
        viewModel.pictureOfTheDay.observe(this) {
            try {
                it.let {
                    binding.apply {
                        tvDate.text = it.date.toString()
                        tvTitle.text = it.title.toString()
                        tvExplanation.text = it.explanation.toString()
                        tvUrl.text = it.url.toString()
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}