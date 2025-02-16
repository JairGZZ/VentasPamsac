package com.jair.ventaspamsac.domain.items

import com.jair.ventaspamsac.data.database.entities.DistrictEntity
data class DistrictItem (
    var id :Int,
    var name: String

    )
fun DistrictEntity.toDistrictItem(): DistrictItem {
    return DistrictItem(
        id = idDistrict,
        name = name
    )
}

fun DistrictItem.toDistrictEntity() = DistrictEntity(
    idDistrict = 0,
    name = this.name
)

