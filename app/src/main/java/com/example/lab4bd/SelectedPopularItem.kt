package com.example.lab4bd

import androidx.room.ColumnInfo

data class SelectedPopularItem(
    @ColumnInfo(name = "item_title") val itemTitle: String,
    @ColumnInfo(name = "item_description") val itemDescription: String,
    @ColumnInfo(name = "item_price") val itemPrice: String,
    @ColumnInfo(name="src")val src:Int=R.drawable.no_image_icon,
)

