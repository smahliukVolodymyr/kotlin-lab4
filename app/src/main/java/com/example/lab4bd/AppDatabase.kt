package com.example.lab4bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class,SearchResults::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun ItemDAO():ItemDAO
    abstract fun SearchResultsDAO():SearchResultsDAO

}