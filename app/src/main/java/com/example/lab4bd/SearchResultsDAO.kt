package com.example.lab4bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SearchResultsDAO {

    @Query("SELECT item.title AS item_title, item.description AS item_description, item.price AS item_price, item.src AS src FROM searchresults INNER JOIN item ON searchresults.item_id = item.uid WHERE searchresults.uid = :searchResultId")
    fun getSelectedItemData(searchResultId: Int): SelectedPopularItem
//
    @Query("SELECT * FROM SearchResults ORDER BY popularity DESC LIMIT 6")
    fun getAll():List<SearchResults>

//    @Query("SELECT * FROM SearchResults ORDER BY popularity DESC LIMIT 6")
//    fun getAll(): LiveData<List<SearchResults>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(result:SearchResults)

    @Update
    fun update(result:SearchResults)

    @Delete
    fun delete(result:SearchResults)

    @Query("UPDATE searchresults SET popularity = popularity + 1 WHERE item_id = :itemId")
    fun incrementPopularity(itemId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM searchresults WHERE item_id = :uid LIMIT 1)")
    fun isUidExists(uid: Int): Boolean

}