package com.example.marvelappkotlin.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (val id: Int, var name: String, val description: String?, val thumbnail: Thumbnail): Parcelable