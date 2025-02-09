package com.example.multipleactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.multipleactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {

            if (binding.editUsername.text.toString().isNotEmpty() && binding.editPassword.text.toString().isNotEmpty()) {

                var username = binding.editUsername.text.toString().trim()
                var password = binding.editPassword.text.toString()

                if ((username.equals("Xeko")) && (password.equals("12345qwerty"))) {

                    binding.editUsername.setText("")
                    binding.editPassword.setText("")

                    Toast.makeText(applicationContext, "Bem Vindo $username!", Toast.LENGTH_LONG).show()

                    //AQUI VAMOS PARA OUTRA ACTIVITY UTILIZANDO O "Intent"
                    val i = Intent(this, PerfilActivity::class.java)
                    startActivity(i)
                    //FECHA A APP PARA IMPEDIR QUE EXISTA UM RETROCESSO INFINITO E PERDA DE DADOS
                    finish()

                } else {

                    Toast.makeText(applicationContext, "Login Inválido!", Toast.LENGTH_LONG).show()

                    val i = Intent(this, FailedActivity::class.java)
                    startActivity(i)

                    finish()

                }

            } else {
                Toast.makeText(applicationContext, "Preencha todos os Campos!", Toast.LENGTH_SHORT).show()
            }

        }

    }

}