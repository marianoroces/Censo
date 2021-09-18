package com.marianoroces.norris.tp3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.marianoroces.norris.tp3.R

class Splash : AppCompatActivity() {

    lateinit var foto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initializeElements()

        foto.setImageResource(R.drawable.logo)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            this.finish()
        }, 1500)
    }

    private fun initializeElements(){
        foto = findViewById(R.id.splash_image)
    }

    //TODO: IMPLEMENTAR TESTS
}