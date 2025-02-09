package com.example.menurestaurante

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.menurestaurante.databinding.ActivitySpashBinding

class SpashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySpashBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        val i = intent
        val bundle = i.extras

        Handler(Looper.getMainLooper()).postDelayed({

            val i = Intent(this, ReceiptActivity::class.java)

            if (bundle != null) {
                i.putExtras(bundle)
            }

            startActivity(i)
            finish()

        }, 3000)

    }
}