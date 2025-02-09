package com.example.calculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        //soma
        binding.buttonSum.setOnClickListener {

            if(!binding.numero1.text.toString().isEmpty() && !binding.numero2.text.toString().isEmpty()) {

                val n1 = binding.numero1.text.toString().toDouble()
                val n2 = binding.numero2.text.toString().toDouble()

                val sum = n1 + n2

                binding.textSolucao.text = String.format("%.2f", sum)

            } else {

                binding.textSolucao.setText("")

            }

        }

        //subtracao
        binding.buttonSub.setOnClickListener {

            //verificar se o campo dos euros não está vazio
            if(!binding.numero1.text.toString().isEmpty() && !binding.numero2.text.toString().isEmpty()) {

                val n1 = binding.numero1.text.toString().toDouble()
                val n2 = binding.numero2.text.toString().toDouble()

                val sub = n1 - n2

                binding.textSolucao.text = String.format("%.2f", sub)
                binding.textSolucao.textAlignment

            } else {

                binding.textSolucao.setText("")

            }

        }

        //multiplicacao
        binding.buttonMul.setOnClickListener {

            //verificar se o campo dos euros não está vazio
            if(!binding.numero1.text.toString().isEmpty() && !binding.numero2.text.toString().isEmpty()) {

                val n1 = binding.numero1.text.toString().toDouble()
                val n2 = binding.numero2.text.toString().toDouble()

                val mul = n1 * n2

                binding.textSolucao.text = String.format("%.2f", mul)
                binding.textSolucao.textAlignment

            } else {

                binding.textSolucao.setText("")

            }

        }

        binding.buttonDiv.setOnClickListener {

            //verificar se o campo dos euros não está vazio
            if(!binding.numero1.text.toString().isEmpty() && !binding.numero2.text.toString().isEmpty()) {

                val n1 = binding.numero1.text.toString().toDouble()
                val n2 = binding.numero2.text.toString().toDouble()

                val div = n1 / n2

                binding.textSolucao.text = String.format("%.2f", div)
                binding.textSolucao.textAlignment

            } else {

                binding.textSolucao.setText("")

            }

        }

    }
}