package fr.bartho.geocarbu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import fr.bartho.geocarbu.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(this.mainLooper).postDelayed({

            startActivity(Intent(this, HomeActivity::class.java))

            finish()

        }, 1000)

    }


}