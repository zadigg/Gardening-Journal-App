package com.example.gardenlog.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garden_table")
data class Garden(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "garden_id")
    val gardenId: Int = 0,

    @ColumnInfo(name = "garden_name")
    val gardenName: String,

    @ColumnInfo(name = "garden_type")
    val gardenType: String,

    @ColumnInfo(name = "watering_frequency")
    val wateringFrequency: String,

    @ColumnInfo(name = "gardening_date")
    val gardeningDate: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gardenId)
        parcel.writeString(gardenName)
        parcel.writeString(gardenType)
        parcel.writeString(wateringFrequency)
        parcel.writeString(gardeningDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Garden> {
        override fun createFromParcel(parcel: Parcel): Garden {
            return Garden(parcel)
        }

        override fun newArray(size: Int): Array<Garden?> {
            return arrayOfNulls(size)
        }
    }
}
