package com.example.contasctslist

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contasctslist.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    private lateinit var dbHelper: DBHelper
    private var contact: Contact? = null
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        // Get userId and contactId from intent
        userId = intent.getIntExtra("userId", -1)
        val contactId = intent.getIntExtra("contactId", -1)

        if (userId == -1) {
            Toast.makeText(this, "Invalid user ID. Returning to main.", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
            return
        }

        if (contactId != -1) {
            contact = dbHelper.getContactById(contactId)
            contact?.let { populateFields(it) }
            binding.buttonDelete.visibility = View.VISIBLE // Show the delete button when editing
        } else {
            binding.buttonDelete.visibility = View.GONE // Hide the delete button when creating
        }

        binding.buttonSave.setOnClickListener { saveContact() }
        binding.buttonCancel.setOnClickListener { finish() }
        binding.buttonDelete.setOnClickListener { deleteContact() }
    }

    private fun populateFields(contact: Contact) {
        binding.editName.setText(contact.name)
        binding.editAdress.setText(contact.address)
        binding.editEmail.setText(contact.email)
        binding.editPhone.setText(contact.phone)
    }

    private fun saveContact() {
        val name = binding.editName.text.toString()
        val address = binding.editAdress.text.toString()
        val email = binding.editEmail.text.toString()
        val phone = binding.editPhone.text.toString()

        if (name.isBlank() || address.isBlank() || email.isBlank() || phone.isBlank()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            return
        }

        if (contact == null) {
            // Add new contact
            val result = dbHelper.contactInsert(userId, name, address, email, phone)
            if (result > 0) {
                Toast.makeText(this, "Contact added successfully!", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Failed to add contact.", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Update existing contact
            val updatedContact = Contact(
                contact!!.id, userId, name, address, email, phone
            )
            val result = dbHelper.contactUpdate(
                updatedContact.id, name, address, email, phone
            )
            if (result > 0) {
                Toast.makeText(this, "Contact updated successfully!", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Failed to update contact.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteContact() {
        contact?.let {
            val result = dbHelper.contactDelete(it.id)
            if (result > 0) {
                Toast.makeText(this, "Contact deleted successfully!", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Failed to delete contact.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
