package com.example.lab4bd
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    @ColumnInfo(name="src") val src:Int,
    @ColumnInfo(name="title") val title:String,
    @ColumnInfo(name="description") val description:String,
    @ColumnInfo(name="price") val price:String
)
