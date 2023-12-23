package com.example.lastversion

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем текущее время и форматируем его
        updateCurrentTime()

        // Создаем таймер, который будет обновлять текущее время каждую секунду
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    updateCurrentTime()
                }
            }
        }, 0, 1000)

        fun makeTime(string: String): Int {
            val time1 = string.split(":")
            return time1[0].toInt()*3600 + time1[1].toInt()*60 + time1[2].toInt()
        }

        button.setOnClickListener{
            val hour = editTextNumber.text.toString().toInt()
            val min = editTextNumber2.text.toString().toInt()
            var sec = editTextNumber3.text.toString().toInt()

            val i = Intent(applicationContext, MyBroadcastReciever::class.java)
            val pi = PendingIntent.getBroadcast(applicationContext, 111, i, PendingIntent.FLAG_IMMUTABLE)
            val am: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val cal = Calendar.getInstance()
            val thour = cal.get(Calendar.HOUR_OF_DAY)
            val tmin = cal.get(Calendar.MINUTE)
            var tsec = cal.get(Calendar.SECOND)

            sec += hour*3600 + min*60
            tsec += thour*3600 + tmin*60
            sec = sec - tsec

            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (sec*1000), pi)
            Toast.makeText(applicationContext, "Будильник прозвонит через $sec секунд", Toast.LENGTH_LONG).show()
        }
    }
    private fun updateCurrentTime() {
        val currentTime = Calendar.getInstance()
        val currentHours = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = currentTime.get(Calendar.MINUTE)
        val currentSeconds = currentTime.get(Calendar.SECOND)
        val formattedCurrentTime = String.format("%02d:%02d:%02d", currentHours, currentMinutes, currentSeconds)
        textView3.text = "$formattedCurrentTime"
    }
}
