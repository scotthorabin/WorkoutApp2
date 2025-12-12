package com.example.workoutapp

import Domain.ItemsModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.workoutapp.databinding.ActivitySpecificItemBinding

class SpecificItemActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpecificItemBinding
    private lateinit var item: ItemsModel


    // Specific item appears based on user input
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySpecificItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

      loadItems()


        val startButton = findViewById<Button>(R.id.startBtn)

        startButton.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
        }

    // Loads Item information
    private fun loadItems(){
        binding.apply{
            item=intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@SpecificItemActivity)
                .load(item.url)
                .into(binding.mainPic)

            Titletxt.text=item.title
            destxt.text=item.description


        }
    }
}