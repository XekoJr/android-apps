package com.example.contasctslist

class Contact(
    val id: Int = 0,
    val userId: Int = 0,
    val name: String = "",
    val address: String = "",
    val email: String = "",
    val phone: String = ""
) {
    override fun toString(): String {
        return "Name: $name, Phone: $phone"
    }
}
