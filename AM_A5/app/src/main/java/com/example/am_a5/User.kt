package com.example.am_a5

import android.net.Uri

class User(
    var name: String,
    var number: String,
    var description: String,
    var country: String,
    val photoUri: Uri
) { }
