package com.example.am_a5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.am_a5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create list of users
        users.add(
            User(
                "João Silva",
                "123456789",
                "Gotta go fast",
                "Portugal",
                Uri.parse("android.resource://com.example.am_a5/drawable/joao_modified")
            )
        )
        users.add(
            User(
                "Maria Oliveira",
                "987654321",
                "Hello!",
                "Brazil",
                Uri.parse("android.resource://com.example.am_a5/drawable/maria_modified")
            )
        )
        users.add(
            User(
                "André Pacheco",
                "987654321",
                "WASAAAA",
                "Portugal",
                Uri.parse("android.resource://com.example.am_a5/drawable/placeholder_modified")
            )
        )
        users.add(
            User(
                "Carlos Santos",
                "555123456",
                "Listening to music",
                "Spain",
                Uri.parse("android.resource://com.example.am_a5/drawable/carlos_modified")
            )
        )
        users.add(
            User(
                "Ana Pereira",
                "800987654",
                "Family >",
                "Portugal",
                Uri.parse("android.resource://com.example.am_a5/drawable/ana_modified")
            )
        )
        users.add(
            User(
                "Liam Johnson",
                "456789123",
                "Hey",
                "United States",
                Uri.parse("android.resource://com.example.am_a5/drawable/liam_modified")
            )
        )

        // Create adapter and set to ListView
        val adapter = UserAdapter(this, users)
        binding.listView.adapter = adapter

        // ListView item click: Edit existing user
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val user = users[position]
            val intent = Intent(this, PerfilActivity::class.java).apply {
                putExtra("pos", position)
                putExtra("name", user.name)
                putExtra("number", user.number)
                putExtra("description", user.description)
                putExtra("country", user.country)
                putExtra("photoUri", user.photoUri.toString())
            }
            startActivityForResult(intent, 100)
        }

        // Add new user
        binding.buttonAdd.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java).apply {
                putExtra("pos", -1) // Indicate new user
            }
            startActivityForResult(intent, 101)
        }
    }

    // Handle data returned from PerfilActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val pos = data.getIntExtra("pos", -1)
            val action = data.getStringExtra("action") // "update" or "delete"

            if (action == "update") {
                val name = data.getStringExtra("name") ?: return
                val number = data.getStringExtra("number") ?: return
                val description = data.getStringExtra("description") ?: return
                val country = data.getStringExtra("country") ?: return
                val photoUri = Uri.parse(data.getStringExtra("photoUri"))

                if (pos == -1) {
                    // Add a new user
                    users.add(
                        User(
                            name,
                            number,
                            description,
                            country,
                            Uri.parse("android.resource://com.example.am_a5/drawable/placeholder_modified")
                        )
                    )
                    Toast.makeText(this, "$name added successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Update an existing user
                    users[pos] = User(name, number, description, country, photoUri)
                    Toast.makeText(this, "$name updated successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (action == "delete" && pos != -1) {
                // Delete a user
                users.removeAt(pos)
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show()
            }

            // Update the list view
            (binding.listView.adapter as UserAdapter).notifyDataSetChanged()
        }
    }

}
