package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import java.util.Random


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
    fun showRandomNumber(){
        val count = intent.getIntExtra("st", 7)
        val random = Random()
        var randomInt = 0

        R.id.textViewRandom = randomInt.toString().toInt()
        R.id.textViewLabel = getString(R.string.random_heading,count).toInt()
    }
}