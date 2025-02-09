package com.example.multipleactivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.multipleactivity.databinding.ActivityPrimeiraBinding

class PrimeiraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrimeiraBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPrimeiraBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        //SLAPSH SCREEN, ou seja, tudo o que estiver dentro do handler vai esperar x ms antes de ser realizado
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)

    }

}