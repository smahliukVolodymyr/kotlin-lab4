package com.example.lab4bd

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import androidx.room.Room

class AppDB : Application() {
    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        createDB()
    }

    private fun createDB() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
    fun testItemsTable(){
        testInsertAllItems()
        testInsertItem()
        testDeleteItem()
        testUpdateItem()
        testReadItem()
    }
    private fun writeMessage(message:String){
        Log.d("Test",message)
    }
    private fun testInsertItem(){
        writeMessage("Insert")
        val dao=db.ItemDAO()
        val item1=Item(243,R.drawable.no_image_icon,"Some title1","Some description1","127$")
        val item2=Item(343,R.drawable.no_image_icon,"Some title2","Some description2","187$")
        dao.insert(item1)
        dao.insert(item2)
    }
    private fun testReadItem(){
        writeMessage("Read all data")
        val dao=db.ItemDAO()
        val items = dao.getAll()
        for(item in items){
            writeMessage("$item")
        }
    }
    private fun testInsertAllItems(){
        writeMessage("Insert all data")
        val dao=db.ItemDAO()
        val item1 = Item(1, R.drawable.no_image_icon, "Eye Patches", "Stylish accessory for any occasion", "129$")
        val item2 = Item(2, R.drawable.no_image_icon, "Bandana", "Fashionable and versatile", "99$")
        val item3 = Item(3, R.drawable.no_image_icon, "Earrings", "Adds a touch of elegance to your look", "49$")
        val item4 = Item(4, R.drawable.no_image_icon, "Necklace", "Perfect for everyday wear", "79$")
        val item5 = Item(5, R.drawable.no_image_icon, "Bracelet", "Statement piece for your wardrobe", "59$")
        val item6 = Item(6, R.drawable.no_image_icon, "Ring", "Classic accessory with a modern twist", "39$")
        val item7 = Item(7, R.drawable.no_image_icon, "Scarf", "Keeps you warm and cozy", "69$")
        val item8 = Item(8, R.drawable.no_image_icon, "Hat", "Protects you from the sun", "89$")
        val item9 = Item(9, R.drawable.no_image_icon, "Sunglasses", "Complete your outfit with these", "149$")
        val item10 = Item(10, R.drawable.no_image_icon, "Gloves", "Comfortable and durable", "79$")
        val item11 = Item(11, R.drawable.no_image_icon, "Belt", "Adjustable and stylish", "59$")
        val item12 = Item(12, R.drawable.no_image_icon, "Socks", "Soft and comfortable to wear", "29$")
        val item13 = Item(13, R.drawable.no_image_icon, "Tie", "Adds a pop of color to your outfit", "39$")
        val item14 = Item(14, R.drawable.no_image_icon, "Watch", "Stay punctual with this stylish accessory", "179$")
        val item15 = Item(15, R.drawable.no_image_icon, "Backpack", "Roomy and practical", "199$")
        val item16 = Item(16, R.drawable.no_image_icon, "Wallet", "Keep your essentials organized", "119$")
        val item17 = Item(17, R.drawable.no_image_icon, "Purse", "Compact and stylish", "99$")
        val item18 = Item(18, R.drawable.no_image_icon, "Shoes", "Dress to impress with these", "169$")
        val item19 = Item(19, R.drawable.no_image_icon, "Boots", "Perfect for any weather", "159$")
        val item20 = Item(20, R.drawable.no_image_icon, "Sandals", "Comfortable and stylish", "109$")

        dao.insertAll(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10,
            item11, item12, item13, item14, item15, item16, item17, item18, item19, item20)

    }
    private fun testDeleteItem(){
        writeMessage("Delete")
        val dao = db.ItemDAO()
        val item2=Item(243,R.drawable.no_image_icon,"New Some title1","New Some description1","127$")
        dao.delete(item2)
    }
    private fun testUpdateItem(){
        writeMessage("Update")
        val dao = db.ItemDAO()
        val item1=Item(243,R.drawable.no_image_icon,"New Some title1","New Some description1","127$")
        dao.update(item1)
    }

    fun getAllSearchResults() {
        writeMessage("Getting all search results")
        val dao = db.SearchResultsDAO()
        val allResults = dao.getAll()
        for(result in allResults) {
            writeMessage("$result")
        }
//        allResults.observe(context as LifecycleOwner) { results ->
//            for(result in results) {
//                writeMessage("$result")
//            }
//        }
    }
    fun testSearchResultsTable(){
        testInsertSearchResults()
        testGetSelectedItemData()
        testDeleteSearchResults()
        testUpdateSearchResults()
        testIncrementSearchResults()
        testGetSelectedItemData()
        getAllSearchResults()
    }
    private fun testInsertSearchResults(){
        writeMessage("Inserting search results")
        val result1 = SearchResults(uid = 1, itemID = 1, popularity = 1)
        val result2 = SearchResults(uid = 2, itemID = 2, popularity = 2)
        val result3 = SearchResults(uid = 3, itemID = 3, popularity = 3)
        val dao = db.SearchResultsDAO()
        dao.insert(result1)
        dao.insert(result2)
        dao.insert(result3)

    }
    private fun testDeleteSearchResults(){
        writeMessage("Deleting search result")
        val result = SearchResults(uid = 1, itemID = 1, popularity = 1)
        val dao = db.SearchResultsDAO()
        dao.delete(result)
    }
    private fun testUpdateSearchResults(){
        writeMessage("Updating search result")
        val result = SearchResults(uid = 2, itemID = 2, popularity = 6)
        val dao = db.SearchResultsDAO()
        dao.update(result)
    }
    private fun testIncrementSearchResults(){
        writeMessage("Increment search result")
        val dao = db.SearchResultsDAO()
        dao.incrementPopularity(3)
    }
    private fun testGetSelectedItemData(){
        writeMessage("Get selected item data")
        val dao = db.SearchResultsDAO()
        val item1 = dao.getSelectedItemData(3)
        val item2 = dao.getSelectedItemData(2)
        writeMessage("$item1")
        writeMessage("$item2")
    }

}
