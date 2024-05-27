package com.example.lab4bd

import SpaceItemDecoration
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as AppDB
        val db = app.db
//        app.testItemsTable()
//        app.testSearchResultsTable()

        app.getAllSearchResults()
        val linearLayout = findViewById<LinearLayout>(R.id.popular_categories_container)
        linearLayout.setOnClickListener { // Код, який виконується при натисканні на LinearLayout

            // Створюємо намір (Intent) для переходу на другу активність
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            // Запускаємо другу активність
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.space_between_items)))

        val dbManager = DBManager(db)

        val dataList = dbManager.getAllStructuredDataAtOnce()

        val adapter = MyAdapter(this, dataList)
        recyclerView.adapter = adapter
        setupSearchView(adapter,dataList)
    }
    private fun setupSearchView(adapter:MyAdapter, dataList:List<SelectedPopularItem>) {
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filtered = filterItems(dataList, newText)
                adapter.setItems(filtered)
                return true
            }
        })
    }
    fun filterItems(
        items: List<SelectedPopularItem>,
        query: String
    ): List<SelectedPopularItem> {
        return items.filter {
            it.itemTitle.contains(query, ignoreCase = true)
        }
    }
}