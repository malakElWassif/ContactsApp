package com.example.contactsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Contact(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    val number: String = ""
) {


}