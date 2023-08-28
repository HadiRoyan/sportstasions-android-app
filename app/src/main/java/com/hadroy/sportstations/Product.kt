package com.hadroy.sportstations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: String,
    val highlight: String,
    val feature: String,
    val number: String,
    val photo: Int
) : Parcelable