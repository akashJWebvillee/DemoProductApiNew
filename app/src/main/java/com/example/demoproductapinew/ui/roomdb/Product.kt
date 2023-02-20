package com.example.demoproductapinew.ui.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products_table")
class Product(

    @PrimaryKey val id: Int,
    val name: String?,
    val imageUrl: String?,
    val price: String?

)