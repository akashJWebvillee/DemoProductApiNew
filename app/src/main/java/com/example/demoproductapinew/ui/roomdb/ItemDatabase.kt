package com.example.demoproductapinew.ui.roomdb

import android.content.Context
import androidx.room.*

@Database(entities = [Product::class], version = 1)

abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "products_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    override fun clearAllTables() {

    }

}