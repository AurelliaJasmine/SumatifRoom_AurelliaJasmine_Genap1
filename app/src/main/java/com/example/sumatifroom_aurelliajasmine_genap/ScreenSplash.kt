package com.example.sumatifroom_aurelliajasmine_genap

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.sumatifroom_aurelliajasmine_genap.Room.codepelita

class ScreenSplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent (this@ScreenSplash,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}