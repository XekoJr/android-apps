package com.example.multipleactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.multipleactivity.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        binding.buttonExit.setOnClickListener {

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

    }
}