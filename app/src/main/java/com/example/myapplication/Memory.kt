package com.example.myapplication

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Memory(
    var id: String? = null,
    var title: String? = null,
    var content: String? = null,
    var date: Date? = null // Nullable Date to allow default value
) {
    // No-argument constructor
    constructor() : this(null, null, null, null)
    fun getDay(): String {
        // Check if date is not null
        date?.let {

            val dateFormat = SimpleDateFormat("dd")

            return dateFormat.format(it)
        }

        return ""
    }

    fun getMonth(): String {
        date?.let {
            val dateFormat = SimpleDateFormat("MMM", Locale.getDefault())
            return dateFormat.format(it)
        }
        return ""
    }

    fun getYear(): String {
        // Check if date is not null
        date?.let {

            val dateFormat = SimpleDateFormat("yyyy")

            return dateFormat.format(it)
        }

        return ""
    }
}
