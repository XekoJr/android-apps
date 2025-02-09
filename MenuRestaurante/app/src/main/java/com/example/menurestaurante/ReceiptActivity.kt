package com.example.menurestaurante

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.menurestaurante.databinding.ActivityReceiptBinding

class ReceiptActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        val i = intent

        val coffee = i.extras?.getInt("coffee") ?: 0
        val bread = i.extras?.getInt("bread") ?: 0
        val chocolate = i.extras?.getInt("chocolate") ?: 0

        var coffeePrice = coffee * 1.0
        var breadPrice = bread * 0.5
        var chocolatePrice = chocolate * 1.2

        if (coffee > 0) {
            binding.textReceipt.append("%.1f€.....$coffee x Café\n".format(coffeePrice))
        }

        if (bread > 0) {
            binding.textReceipt.append("%.1f€.....$bread x Pão\n".format(breadPrice))
        }

        if (chocolate > 0) {
            binding.textReceipt.append("%.1f€.....$chocolate x Chocolate".format(chocolatePrice))
        }

        val total = coffeePrice + breadPrice + chocolatePrice
        binding.textTotal.text = String.format("%.1f €", total)

    }
}