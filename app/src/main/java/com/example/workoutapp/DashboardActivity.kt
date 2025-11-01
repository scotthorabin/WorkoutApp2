package com.example.workoutapp

import Adapter.CategoryAdapter
import Adapter.PopularAdapter
import ViewModel.MainViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R.id.history
import com.example.workoutapp.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView



class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initPopular()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
       bottomNav.setOnItemSelectedListener() { item ->
           when(item.itemId) {
               history -> {
                   val intent = Intent(this, MainActivity::class.java)
                   startActivity(intent)
                   true
               }
               else -> false
           }
        }
    }

    private fun initPopular() {
        binding.popularProgressBar.visibility = View.VISIBLE

        viewModel.loadPopular().observe(this) { popularList ->
            Log.d("Dashboard", "Popular list size: ${popularList.size}")
            binding.popularView.layoutManager = GridLayoutManager(this, 2)
            binding.popularView.adapter = PopularAdapter(popularList)
            binding.popularProgressBar.visibility = View.GONE
        }
    }

    private fun initCategory(){
        // When exercises are loading, progress bar is visible //
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.categoryView
                .layoutManager = LinearLayoutManager(
                this@DashboardActivity, LinearLayoutManager.HORIZONTAL, false
            )
            // Sets new adapter
            binding.categoryView.adapter = CategoryAdapter(it)
            // When all exercises are loaded, then progress bar disappears //
            binding.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()

    }
}