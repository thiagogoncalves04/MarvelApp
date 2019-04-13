package com.example.marvelappkotlin.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Character (val id: Int, var name: String, val description: String?, val thumbnail: Thumbnail, val modified: Date): Parcelable