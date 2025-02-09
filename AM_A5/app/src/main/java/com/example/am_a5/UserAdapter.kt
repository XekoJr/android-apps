package com.example.am_a5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(
    context: Context,
    private val users: List<User>
) : ArrayAdapter<User>(
    context,
    R.layout.item_user,
    users
) {  // Use item_user.xml as the item layout for each row in the ListView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        val photo = view.findViewById<ImageView>(R.id.contact_photo)
        val name = view.findViewById<TextView>(R.id.contact_name)
        val description = view.findViewById<TextView>(R.id.contact_description)

        val user = users[position]

        name.text = user.name
        description.text = user.description

        if (user.photoUri != null) {
            photo.setImageURI(user.photoUri)
        } else {
            photo.setImageResource(R.drawable.placeholder_modified)
        }

        return view
    }

}
