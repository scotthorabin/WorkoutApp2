package com.example.workoutapp

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text




class TimerActivity : AppCompatActivity() {


    //Variables
    private var selectedTime: Int = 0
    private var countdownTimer: CountDownTimer? = null
    private var progressT: Int = 0
    private var pauseOffSet: Long = 0
    private var start: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)

        // When clicked, goes to set time function
        val addTimeBtn = findViewById<ImageButton>(R.id.addBtn)

        addTimeBtn.setOnClickListener {
            setTime()
        }

        // When clicked on, goes to timerSetup function
        val startBtn = findViewById<Button>(R.id.btnPlayPause)

        startBtn.setOnClickListener {
            timerSetup()
        }

        // When clicked on, goes to ResetTime function
        val resetBtn = findViewById<ImageButton>(R.id.resetBtn)
        resetBtn.setOnClickListener {
            resetTime()
        }

        // When clicked on, goes to addExtraTime function
        val addTime = findViewById<TextView>(R.id.SecondsP)
        addTime.setOnClickListener {
            addExtraTime()
        }
    }


    // Function that Set ups timer
    private fun timerSetup(){
    val startBtn = findViewById<Button>(R.id.btnPlayPause)
        if(selectedTime > progressT)
        {
            if (start)
            {
                startBtn.text = "Pause"
                startTime(pauseOffSet)
                start = false
            }
            else
            {
                start = true
                startBtn.text = "Resume"
                timePause()
            }
        }
        else
        {
            Toast.makeText(this, "Please enter a time", Toast.LENGTH_SHORT).show()
        }
    }

    // Function that pauses Timer
    private fun timePause() {
        if (countdownTimer!= null)
        {
            countdownTimer!!.cancel()
        }
    }


    // Function that starts timer
    private fun startTime(pauseOffSetL: Long)
    {
        val progress = findViewById<ProgressBar>(R.id.pbTimer)
        progress.progress = progressT
        countdownTimer = object : CountDownTimer(
            (selectedTime*1000).toLong() - pauseOffSetL * 1000, 1000
        ) {
            override fun onFinish() {
                Toast.makeText(this@TimerActivity, "Time is Up!", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(p0: Long) {
                progressT++
                pauseOffSet = selectedTime.toLong() - p0/1000
                progress.progress = selectedTime - progressT
                val timeLeft = findViewById<TextView>(R.id.TimeLeft)
                timeLeft.text =(selectedTime - progressT).toString()
            }

        }.start()
    }


    // Function for when User sets amount of time, changes progress bar
    private fun setTime() {

        val timeDialog = Dialog(this)
        timeDialog.setContentView(R.layout.add_timer)
        val setTime = timeDialog.findViewById<EditText>(R.id.getUserTime)
        val timeLeft = findViewById<TextView>(R.id.TimeLeft)
        val startBtn = findViewById<Button>(R.id.btnPlayPause)
        val progress = findViewById<ProgressBar>(R.id.pbTimer)
        timeDialog.findViewById<Button>(R.id.ConfirmBtn).setOnClickListener {

            // If time is empty, then message appears, else get time and apply to XML
            if (setTime.text.isEmpty())
            {
                Toast.makeText(this, "Please enter a time duration", Toast.LENGTH_SHORT).show()
            }
            else {
                timeLeft.text = setTime.text
                startBtn.text = "Start"
                selectedTime = setTime.text.toString().toInt()
                progress.max = selectedTime
            }
            timeDialog.dismiss()
        }
        timeDialog.show()

    }

    // Function that resets time
    private fun resetTime() {
        countdownTimer!!.cancel()
        progressT = 0
        selectedTime = 0
        pauseOffSet = 0
        countdownTimer = null
        val startBtn = findViewById<Button>(R.id.btnPlayPause)
        startBtn.text = "Start"
        start = true
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        progressBar.progress = 0
        val timeLeft = findViewById<TextView>(R.id.TimeLeft)
        timeLeft.text = "0"
    }

    // Function that adds 10 seconds on when user clicks TextView
    private fun addExtraTime() {
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        if (selectedTime!=0)
        {
            selectedTime += 10
            progressBar.max = selectedTime
            timePause()
            startTime(pauseOffSet)
            Toast.makeText(this, "10 seconds added on", Toast.LENGTH_SHORT).show()
        }
    }

    // Function that removes Timer countdown
    override fun onDestroy() {
        super.onDestroy()
        if(countdownTimer!= null)
            countdownTimer?.cancel()
        progressT = 0
    }


}
