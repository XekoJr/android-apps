package com.example.contasctslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contasctslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Contact>
    private lateinit var dbHelper: DBHelper
    private var contacts = mutableListOf<Contact>()
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        userId = intent.getIntExtra("userId", -1)
        if (userId == -1) {
            Toast.makeText(this, "Invalid user ID. Redirecting to Login.", Toast.LENGTH_SHORT).show()
            redirectToLogin()
            return
        }

        // Initialize the adapter with an empty contact list
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts)
        binding.list.adapter = adapter

        // Load the contacts from the database
        loadContacts()

        // Logout button
        binding.buttonLogout.setOnClickListener {
            getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
            redirectToLogin()
        }

        // Add new contact
        binding.buttonNewContact.setOnClickListener {
            val intent = Intent(this, NewContactActivity::class.java)
            intent.putExtra("userId", userId) // Pass the userId to NewContactActivity
            startActivityForResult(intent, 100) // Use requestCode 100 for adding a new contact
        }

        // Edit an existing contact
        binding.list.setOnItemClickListener { _, _, position, _ ->
            val contact = contacts[position]
            val intent = Intent(this, NewContactActivity::class.java)
            intent.putExtra("userId", userId) // Pass the userId
            intent.putExtra("contactId", contact.id) // Pass the contactId
            startActivityForResult(intent, 101) // Use requestCode 101 for editing a contact
        }
    }

    private fun loadContacts() {
        contacts.clear()
        contacts.addAll(dbHelper.getAllContacts(userId))
        adapter.notifyDataSetChanged() // Notify the adapter that the data set has changed
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Handle results from NewContactActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                100, 101 -> { // Refresh the contact list after adding or editing
                    loadContacts()
                }
            }
        }
    }
}
