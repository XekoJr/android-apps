package com.example.contasctslist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {

    // SQL statements for creating the tables
    private val sqlStatements = arrayOf(
        "CREATE TABLE user (U_ID INTEGER PRIMARY KEY AUTOINCREMENT, U_NAME TEXT NOT NULL, U_PASSWORD TEXT NOT NULL)",
        "INSERT INTO user (U_NAME, U_PASSWORD) VALUES ('admin', 'admin')",
        "INSERT INTO user (U_NAME, U_PASSWORD) VALUES ('user', 'user')",

        "CREATE TABLE contacts (C_ID INTEGER PRIMARY KEY AUTOINCREMENT, U_ID INTEGER NOT NULL, C_NAME TEXT NOT NULL, C_ADDRESS TEXT, C_EMAIL TEXT, C_PHONE TEXT, FOREIGN KEY (U_ID) REFERENCES user (U_ID))"
    )

    // Called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase) {
        sqlStatements.forEach { db.execSQL(it) }
    }

    // Called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS contacts")
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    // --- User Methods ---

    fun userInsert(name: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("U_NAME", name)
            put("U_PASSWORD", password)
        }
        val result = db.insert("user", null, contentValues)
        if (result == -1L) {
            Log.e("DBHelper", "Failed to insert user: $name")
        } else {
            Log.d("DBHelper", "User inserted with ID: $result")
        }
        db.close()
        return result
    }

    fun isUsernameTaken(username: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM user WHERE U_NAME = ?", arrayOf(username))
        var isTaken = false
        if (cursor.moveToFirst()) {
            isTaken = cursor.getInt(0) > 0 // Check if count > 0
        }
        cursor.close()
        return isTaken
    }


    fun getUserByUsernameAndPassword(username: String, password: String): User? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM user WHERE U_NAME = ? AND U_PASSWORD = ?", arrayOf(username, password))
        return if (cursor.moveToFirst()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndexOrThrow("U_ID")),
                cursor.getString(cursor.getColumnIndexOrThrow("U_NAME")),
                cursor.getString(cursor.getColumnIndexOrThrow("U_PASSWORD"))
            )
            cursor.close()
            user
        } else {
            cursor.close()
            null
        }
    }

    // --- Contact Methods ---

    fun contactInsert(userId: Int, name: String, address: String?, email: String?, phone: String?): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("U_ID", userId)
            put("C_NAME", name)
            put("C_ADDRESS", address)
            put("C_EMAIL", email)
            put("C_PHONE", phone)
        }
        val result = db.insert("contacts", null, contentValues)
        db.close()
        return result
    }

    fun contactUpdate(contactId: Int, name: String, address: String?, email: String?, phone: String?): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("C_NAME", name)
            put("C_ADDRESS", address)
            put("C_EMAIL", email)
            put("C_PHONE", phone)
        }
        val result = db.update("contacts", contentValues, "C_ID = ?", arrayOf(contactId.toString()))
        db.close()
        return result
    }

    fun contactDelete(contactId: Int): Int {
        val db = this.writableDatabase
        val result = db.delete("contacts", "C_ID = ?", arrayOf(contactId.toString()))
        db.close()
        return result
    }

    fun contactSelectAll(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM contacts", null)
    }

    fun getAllContacts(userId: Int): List<Contact> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM contacts WHERE U_ID = ?", arrayOf(userId.toString()))
        val contacts = mutableListOf<Contact>()
        if (cursor.moveToFirst()) {
            do {
                val contact = Contact(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("C_ID")),
                    userId = userId,
                    name = cursor.getString(cursor.getColumnIndexOrThrow("C_NAME")),
                    address = cursor.getString(cursor.getColumnIndexOrThrow("C_ADDRESS")) ?: "",
                    email = cursor.getString(cursor.getColumnIndexOrThrow("C_EMAIL")) ?: "",
                    phone = cursor.getString(cursor.getColumnIndexOrThrow("C_PHONE")) ?: ""
                )
                contacts.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contacts
    }

    fun getContactById(contactId: Int): Contact? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM contacts WHERE C_ID = ?", arrayOf(contactId.toString()))
        return if (cursor.moveToFirst()) {
            val contact = Contact(
                cursor.getInt(cursor.getColumnIndexOrThrow("C_ID")),
                cursor.getInt(cursor.getColumnIndexOrThrow("U_ID")),
                cursor.getString(cursor.getColumnIndexOrThrow("C_NAME")),
                cursor.getString(cursor.getColumnIndexOrThrow("C_ADDRESS")) ?: "",
                cursor.getString(cursor.getColumnIndexOrThrow("C_EMAIL")) ?: "",
                cursor.getString(cursor.getColumnIndexOrThrow("C_PHONE")) ?: ""
            )
            cursor.close()
            contact
        } else {
            cursor.close()
            null
        }
    }
}
