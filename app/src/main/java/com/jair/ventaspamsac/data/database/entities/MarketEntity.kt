package com.jair.ventaspamsac.data.database.entities


data class MarketEntity(

    var id: String = "",
    var name: String = "",
    var district: String = ""

)
 fun MarketEntity.toMap(): Map<String, Any> = hashMapOf(
    "name" to name,
    "district" to district
)


