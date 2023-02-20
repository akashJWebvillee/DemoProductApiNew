package com.example.demoproductapinew.ui.roomdb

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM Products_table")
    fun getAllItems(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Product)

    @Delete
    fun deleteItem(item: Product)

}
