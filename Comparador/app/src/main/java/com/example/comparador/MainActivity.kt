package com.example.comparador

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.comparador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.button12.setOnClickListener {

            var cont = filled()

            if(cont) {

                var val1 = binding.edit1.text.toString().toInt()
                var val2 = binding.edit2.text.toString().toInt()

                comp(val1, val2, 1, 2)

            }

        }

        binding.button13.setOnClickListener {

            var cont = filled()

            if(cont) {

                var val1 = binding.edit1.text.toString().toInt()
                var val2 = binding.edit3.text.toString().toInt()

                comp(val1, val2, 1, 3)

            }

        }

        binding.button23.setOnClickListener {

            var cont = filled()

            if (cont) {

                var val1 = binding.edit2.text.toString().toInt()
                var val2 = binding.edit3.text.toString().toInt()

                comp(val1, val2, 2, 3)

            }

        }

    }

    private fun filled():Boolean {

        if(!binding.edit1.text.toString().isEmpty() && !binding.edit2.text.toString().isEmpty() && !binding.edit3.text.toString().isEmpty())
            return true
        else {
            Toast.makeText(applicationContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()

            binding.textComp.text = ""
            if (binding.edit1.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Preencha o valor 1!", Toast.LENGTH_SHORT).show()
            }
            if (binding.edit2.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Preencha o valor 2!", Toast.LENGTH_SHORT).show()
            }
            if (binding.edit3.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Preencha o valor 3!", Toast.LENGTH_SHORT).show()
            }

            return false
        }
    }

    private fun comp(num1: Int, num2: Int, caixa1: Int, caixa2: Int) {

        if (num1 > num2)
            binding.textComp.text = "Entre as caixas $caixa1 e $caixa2 o valor mais alto é:\n $caixa1 com o valor de $num1"
        else if (num2 > num1)
            binding.textComp.text = "Entre as caixas $caixa1 e $caixa2 o valor mais alto é:\n $caixa2 com o valor de $num2"
        else
            binding.textComp.text = "Os valores são Iguais!"

    }
}