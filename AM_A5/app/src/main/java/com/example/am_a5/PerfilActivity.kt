package com.example.am_a5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.am_a5.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pos = intent.getIntExtra("pos", -1)
        val name = intent.getStringExtra("name") ?: ""
        val number = intent.getStringExtra("number") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val country = intent.getStringExtra("country") ?: ""
        val photoUriString = intent.getStringExtra("photoUri") ?: ""
        val photoUri = if (photoUriString.isNotEmpty()) Uri.parse(photoUriString) else null

        // Populate UI fields
        binding.editTextName.setText(name)
        binding.editTextNumber.setText(number)
        binding.editTextDescription.setText(description)
        binding.editTextCountry.setText(country)
        if (photoUri != null) {
            binding.contactPhoto.setImageURI(photoUri)
        } else {
            binding.contactPhoto.setImageResource(R.drawable.placeholder_modified)
        }

        // Hide delete button if new user is being added
        if (pos == -1) {
            binding.buttonDelete.visibility = View.GONE
        } else {
            binding.buttonDelete.visibility = View.VISIBLE
        }

        // Save button
        binding.buttonSave.setOnClickListener {
            if (binding.editTextName.text.isNotEmpty() &&
                binding.editTextNumber.text.isNotEmpty() &&
                binding.editTextNumber.text.length == 9 &&
                binding.editTextDescription.text.isNotEmpty() &&
                binding.editTextCountry.text.isNotEmpty()) {

                val resultIntent = Intent().apply {
                    putExtra("action", "update") // Indicates update
                    putExtra("pos", pos)
                    putExtra("name", binding.editTextName.text.toString())
                    putExtra("number", binding.editTextNumber.text.toString())
                    putExtra("description", binding.editTextDescription.text.toString())
                    putExtra("country", binding.editTextCountry.text.toString())
                    putExtra("photoUri", photoUri?.toString() ?: "")
                }
                setResult(RESULT_OK, resultIntent)
                finish()

            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }
        }

        // Botão Apagar
        binding.buttonDelete.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("action", "delete") // Indicates delete
                putExtra("pos", pos)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

}
