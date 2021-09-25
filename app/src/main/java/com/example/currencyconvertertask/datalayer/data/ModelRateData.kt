package com.example.currencyconvertertask.datalayer.data

import android.os.Parcel
import android.os.Parcelable


data class ModelRateData(val key: String?, val rateValue:Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeDouble(rateValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelRateData> {
        override fun createFromParcel(parcel: Parcel): ModelRateData {
            return ModelRateData(parcel)
        }

        override fun newArray(size: Int): Array<ModelRateData?> {
            return arrayOfNulls(size)
        }
    }
}