package ru.kpfu.itis.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun randomMe(view: View){
        val randomIntent = Intent(this,SecondActivity::class.java)
        randomIntent.putExtra("st", 2)
        startActivity(randomIntent)
    }
}