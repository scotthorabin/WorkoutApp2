package com.example.workoutapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class forgotPasswordActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        // Variables for user to fill out email form and submit request
        val submit = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_Submit)
        val email = findViewById<TextInputEditText>(R.id.email)

        // IF statements for both field being activated/not activated
        submit.setOnClickListener {
            val email: String = email.text.toString().trim{ it <= ' '}
            if (email.isEmpty()){
                Toast.makeText(this@forgotPasswordActivity, "Please enter email address.", Toast.LENGTH_SHORT).show()
        } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Instructions have been sent to your email", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}