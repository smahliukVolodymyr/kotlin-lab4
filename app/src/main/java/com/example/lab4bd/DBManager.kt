package com.example.lab4bd

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner

class DBManager(private val db: AppDatabase) {

    fun readDataFromItemTable(): List<Item> {
        val dao = db.ItemDAO()
        return dao.getAll()
    }

    fun getAllStructuredDataAtOnce():List<SelectedPopularItem>{
        val dao = db.SearchResultsDAO()
        val tableData = dao.getAll()
        val resultList = mutableListOf<SelectedPopularItem>()
        for (data in tableData){
            val item =dao.getSelectedItemData(data.uid)
            resultList.add(item)
        }
        return resultList
    }
//fun getAllStructuredDataAtOnce(context: Context, callback: (List<SelectedPopularItem>) -> Unit) {
//        val dao = db.SearchResultsDAO()
//        val tableData = dao.getAll()
//        val resultList = mutableListOf<SelectedPopularItem>()
//
//        tableData.observe(context as LifecycleOwner) { results ->
//            for (data in results) {
//                val item = dao.getSelectedItemData(data.uid)
//                resultList.add(item)
//            }
//            // Викликаємо зворотній виклик із результатами, коли вони готові
//            callback(resultList)
//        }
//    }
    fun addNewPopularItemsToDatabase(items: List<Item>) {
        val dao = db.SearchResultsDAO()
        items.forEach { item ->
            if (!dao.isUidExists(item.uid)) {

                dao.insert(SearchResults(0,item.uid, 1))
            }
            else{
                dao.incrementPopularity(item.uid)
            }
        }
    }

}
