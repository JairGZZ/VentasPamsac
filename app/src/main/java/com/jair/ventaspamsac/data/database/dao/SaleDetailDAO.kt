package com.jair.ventaspamsac.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jair.ventaspamsac.data.database.entities.SaleDetailEntity

@Dao
interface SaleDetailDAO {
    @Query("SELECT * FROM sale_details")
    suspend fun getAll(): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE id_sale_detail = :id")
    suspend fun getById(id: Int): SaleDetailEntity

    @Query("SELECT * FROM sale_details WHERE id_sale = :idSale")
    suspend fun getBySale(idSale: Int): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE id_product = :idProduct")
    suspend fun getByProduct(idProduct: Int): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE quantity = :quantity")
    suspend fun getByQuantity(quantity: Int): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE subtotal = :subtotal")
    suspend fun getBySubtotal(subtotal: Double): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE id_sale = :idSale AND id_product = :idProduct")
    suspend fun getBySaleAndProduct(idSale: Int, idProduct: Int): SaleDetailEntity

    @Query("SELECT * FROM sale_details WHERE id_sale = :idSale AND quantity = :quantity")
    suspend fun getBySaleAndQuantity(idSale: Int, quantity: Int): List<SaleDetailEntity>

    @Query("SELECT * FROM sale_details WHERE id_sale = :idSale AND subtotal = :subtotal")
    suspend fun getBySaleAndSubtotal(idSale: Int, subtotal: Double): List<SaleDetailEntity>

    @Insert
    suspend fun insert(saleDetail: SaleDetailEntity)

    @Query("DELETE FROM sale_details WHERE id_sale_detail = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM sale_details WHERE id_sale = :idSale")
    suspend fun deleteBySale(idSale: Int)

    @Query("DELETE FROM sale_details WHERE id_product = :idProduct")
    suspend fun deleteByProduct(idProduct: Int)

    @Query("DELETE FROM sale_details WHERE quantity = :quantity")
    suspend fun deleteByQuantity(quantity: Int)

    @Query("DELETE FROM sale_details WHERE subtotal = :subtotal")
    suspend fun deleteBySubtotal(subtotal: Double)


}