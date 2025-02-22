package com.jair.ventaspamsac.di

import android.content.Context
import androidx.room.Room
import com.jair.ventaspamsac.data.database.DatabaseContext
import com.jair.ventaspamsac.data.database.dao.ClientDAO
//import com.jair.ventaspamsac.data.database.dao.DistrictDAO
import com.jair.ventaspamsac.data.database.dao.MarketDAO
import com.jair.ventaspamsac.data.database.dao.NoteDAO
//import com.jair.ventaspamsac.data.database.dao.ProductDAO
//import com.jair.ventaspamsac.data.database.dao.SaleDAO
//import com.jair.ventaspamsac.data.database.dao.SaleDetailDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "pamsac_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, DatabaseContext::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideClientDAO(db: DatabaseContext): ClientDAO = db.clientDAO()

    @Singleton
    @Provides
    fun provideNoteDAO(db: DatabaseContext): NoteDAO = db.noteDAO()

    @Singleton
    @Provides
    fun provideMarketDAO(db: DatabaseContext): MarketDAO = db.marketDAO()

//    @Singleton
//    @Provides
//    fun provideDistrictDAO(db: DatabaseContext): DistrictDAO = db.districtDAO()
//
//    @Singleton
//    @Provides
//    fun provideProductDAO(db: DatabaseContext): ProductDAO = db.productDAO()
//
//    @Singleton
//    @Provides
//    fun provideSaleDAO(db: DatabaseContext): SaleDAO = db.saleDAO()
//
//    @Singleton
//    @Provides
//    fun provideSaleDetailDAO(db: DatabaseContext): SaleDetailDAO = db.saleDetailDAO()

}