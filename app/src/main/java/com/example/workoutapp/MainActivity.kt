package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)




        //Adding animations
        val movingItems = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.ttb)



        //Initialising TextViews on Main
        val image = findViewById<ImageView>(R.id.Background)
        val login = findViewById<Button>(R.id.Login)
        val text = findViewById<TextView>(R.id.slogan)
        val logo = findViewById<ImageView>(R.id.Logo)
        val getStarted = findViewById<Button>(R.id.getStarted)



        // Adding animations to items
        text.startAnimation(movingItems)
        logo.startAnimation(movingItems)
        image.startAnimation(movingItems)
        login.startAnimation(movingItems)
        getStarted.startAnimation(movingItems)

        // When get started button is clicked, navigate to register page
        getStarted.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // When get login button is clicked, navigate to register page
        login.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

    }
}