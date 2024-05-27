package com.example.lab4bd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDAO {
    @Query("SELECT * FROM item")
    fun getAll():List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg items:Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item:Item)

    @Update
    fun update(item:Item)

    @Delete
    fun delete(item:Item)
}