package com.example.lab4bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Item::class,
    parentColumns = ["uid"],
    childColumns = ["item_id"],
    onDelete = ForeignKey.CASCADE
)])
data class SearchResults(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "item_id") val itemID: Int,
    @ColumnInfo(name="popularity") val popularity: Int,
)
