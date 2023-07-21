package com.marwendevs.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //variable for Timer which will be initialized later
    private var countDownTimer: CountDownTimer? = null
    // the duration of the timer in milliseconds *1 second is equal to 1000 milliseconds
    private var timerDuration: Long = 60000
    //pauseOffset = timerDuration - timer left
    private var pauseOffset : Long = 0
    var tvTimer : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        tvTimer = findViewById(R.id.tv_countDown)
        val btnStart: Button = findViewById(R.id.btn_Start)
        val btnPause: Button = findViewById(R.id.btn_Pause)
        val btnReset: Button = findViewById(R.id.btn_Reset)
        tvTimer?.text = "${(timerDuration/1000).toString()}"
        btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }
        btnPause.setOnClickListener {
            pauseTimer()
        }
        btnReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        tvTimer?.text = "${(timerDuration/1000).toString()}"
        countDownTimer = null
        pauseOffset = 0
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
    }

    private fun startTimer(pauseOffsetL : Long){
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL,1000)
        {
            override fun onTick(millisUntilFinished : Long ){
                pauseOffset = timerDuration - millisUntilFinished
                tvTimer?.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Timer is Finished",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}