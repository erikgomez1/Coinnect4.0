package com.example.coinnect

import android.os.Parcel
import android.os.Parcelable

data class Cryptocurrency(
    val cryptoName: String,
    val symbol: String,
    val cryptoPrice: Double,
    val imageUrl: String,
    var isFavorite: Boolean = false,
    var notes: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cryptoName)
        parcel.writeString(symbol)
        parcel.writeDouble(cryptoPrice)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Cryptocurrency> {
        override fun createFromParcel(parcel: Parcel): Cryptocurrency {
            return Cryptocurrency(parcel)
        }

        override fun newArray(size: Int): Array<Cryptocurrency?> {
            return arrayOfNulls(size)
        }
    }
}
