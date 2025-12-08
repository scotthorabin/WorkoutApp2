package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)




        //Adding animations
        val movingItems = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.ttb)



        //Initialising TextViews on Main
        val image = findViewById<ImageView>(R.id.mainImage)
        val text = findViewById<TextView>(R.id.slogan)
        val getStarted = findViewById<Button>(R.id.getStarted)



        // Adding animations to items
        image.startAnimation(movingItems)
        text.startAnimation(movingItems)
        getStarted.startAnimation(movingItems)

        // When get started button is clicked, navigate to register page
        getStarted.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}