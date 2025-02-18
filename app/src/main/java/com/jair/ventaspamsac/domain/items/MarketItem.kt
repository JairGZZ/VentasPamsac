package com.jair.ventaspamsac.domain.items

import android.os.Parcel
import android.os.Parcelable
import com.jair.ventaspamsac.data.database.entities.MarketEntity

data class MarketItem(
    var idMarket:Int,
    var name:String,

    var district:String)
//: Parcelable{
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString()!!,
//        parcel.readString()!!
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(idMarket)
//        parcel.writeString(name)
//        parcel.writeString(district)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<MarketItem> {
//        override fun createFromParcel(parcel: Parcel): MarketItem {
//            return MarketItem(parcel)
//        }
//
//        override fun newArray(size: Int): Array<MarketItem?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}
fun MarketEntity.toMarketItem(): MarketItem {
    return MarketItem(
        idMarket = idMarket,
        name = name,
        district = district
    )
}

fun MarketItem.toMarketEntity() = MarketEntity(
    idMarket = 0,
    name = name,
    district = district

)
