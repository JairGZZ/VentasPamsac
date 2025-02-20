package com.jair.ventaspamsac.domain.items

import com.jair.ventaspamsac.data.database.entities.ClientEntity

data class ClientItem(

    var idClient:Int,
    var name:String,
    var lastName:String,
    var phone:String,
    var storeNumber:String,
    var idMarket:Int
)
fun ClientEntity.toClientItem(): ClientItem{
    return ClientItem(
        idClient = idClient,
        name = name,
        lastName = lastName,
        phone = phone,
        storeNumber = storeNumber,
        idMarket = idMarket
    )
}
fun ClientItem.toClientEntity() = ClientEntity(
    idClient = 0,
    name = name,
    lastName = lastName,
    phone = phone,
    storeNumber = storeNumber,
    idMarket = idMarket
)


