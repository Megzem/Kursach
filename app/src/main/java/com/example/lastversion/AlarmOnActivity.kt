package com.example.lastversion

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder.AudioSource
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_alarm_on.*

class AlarmOnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_on)

        var mp = MediaPlayer.create(applicationContext, R.raw.pinguinos)
        mp.start()
        mp.setLooping(true)

        button2.setOnClickListener{
            mp.stop()
            Handler(Looper.getMainLooper())
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        }
    }
}