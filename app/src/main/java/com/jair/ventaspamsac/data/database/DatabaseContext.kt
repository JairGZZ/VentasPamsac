package com.jair.ventaspamsac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jair.ventaspamsac.data.database.dao.ClientDAO
import com.jair.ventaspamsac.data.database.dao.DistrictDAO
import com.jair.ventaspamsac.data.database.dao.MarketDAO
import com.jair.ventaspamsac.data.database.dao.NoteDAO
import com.jair.ventaspamsac.data.database.dao.ProductDAO
import com.jair.ventaspamsac.data.database.dao.SaleDAO
import com.jair.ventaspamsac.data.database.dao.SaleDetailDAO
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.data.database.entities.DistrictEntity
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.data.database.entities.NoteEntity
import com.jair.ventaspamsac.data.database.entities.ProductEntity
import com.jair.ventaspamsac.data.database.entities.SaleDetailEntity
import com.jair.ventaspamsac.data.database.entities.SaleEntity

@Database(
    [ClientEntity::class, MarketEntity::class, DistrictEntity::class, ProductEntity::class, SaleEntity::class, SaleDetailEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class DatabaseContext : RoomDatabase() {
    abstract fun clientDAO(): ClientDAO
    abstract fun marketDAO(): MarketDAO
    abstract fun districtDAO(): DistrictDAO
    abstract fun productDAO(): ProductDAO
    abstract fun saleDAO(): SaleDAO
    abstract fun saleDetailDAO(): SaleDetailDAO
    abstract fun noteDAO(): NoteDAO

}