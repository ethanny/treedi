package com.ar.treedi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TreeData(
    val nativeName: String,
    val scientificName: String,
    val description: String,
    val otherNames: String,
    val family: String,
    val genus: String,
    val nativeLocation: String,
    val climate: String,
    val system: String,
    val habitatType: String,
    val endemicity: String,
    val uses: String
) : Parcelable