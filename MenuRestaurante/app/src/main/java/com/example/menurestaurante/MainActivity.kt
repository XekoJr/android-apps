package com.example.menurestaurante

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.menurestaurante.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        val coffeeCheck = findViewById<CheckBox>(R.id.check_coffee)
        coffeeCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                binding.editCoffee.setText("1")
                binding.textCoffee.setText("1.0€")

            } else {

                binding.editCoffee.setText("")
                binding.textCoffee.setText("")

            }
        }

        val breadCheck = findViewById<CheckBox>(R.id.check_bread)
        breadCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                binding.editBread.setText("1")
                binding.textBread.setText("0.5€")

            } else {

                binding.editBread.setText("")
                binding.textBread.setText("")

            }
        }

        val chocolateCheck = findViewById<CheckBox>(R.id.check_chocolate)
        chocolateCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                binding.editChocolate.setText("1")
                binding.textChocolate.setText("1.2€")

            } else {

                binding.editChocolate.setText("")
                binding.textChocolate.setText("")

            }
        }

        binding.buttonOrder.setOnClickListener {
            val isCoffeeValid = binding.checkCoffee.isChecked &&
                    (binding.editCoffee.text.toString().toIntOrNull() ?: 0) > 0

            val isBreadValid = binding.checkBread.isChecked &&
                    (binding.editBread.text.toString().toIntOrNull() ?: 0) > 0

            val isChocolateValid = binding.checkChocolate.isChecked &&
                    (binding.editChocolate.text.toString().toIntOrNull() ?: 0) > 0

            if (isCoffeeValid || isBreadValid || isChocolateValid) {

                val i = Intent(this, SpashActivity::class.java)

                if (isCoffeeValid) {
                    val coffee = binding.editCoffee.text.toString().toIntOrNull() ?: 0
                    i.putExtra("coffee", coffee)
                }

                if (isBreadValid) {
                    val bread = binding.editBread.text.toString().toIntOrNull() ?: 0
                    i.putExtra("bread", bread)
                }

                if (isChocolateValid) {
                    val chocolate = binding.editChocolate.text.toString().toIntOrNull() ?: 0
                    i.putExtra("chocolate", chocolate)
                }

                startActivity(i)
                finish()

            } else {
                Toast.makeText(this, "Selecione pelo menos um produto", Toast.LENGTH_SHORT).show()
            }

        }

    }
}