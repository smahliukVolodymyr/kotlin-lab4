package com.example.lab4bd

import ButtonAdapter
import ButtonItem
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // звертаюсь до бд
        val app = application as AppDB
        val db = app.db

        val dbManager = DBManager(db)
        // Отримую дані перд відображенням
        val resultsList =dbManager.readDataFromItemTable()

        val recyclerView: RecyclerView = findViewById(R.id.horizontal_recycler_view)
        val buttonList = listOf(
            ButtonItem("Lorem", Color.GRAY, Color.WHITE),
            ButtonItem("Lorem Ipsum", Color.LTGRAY, Color.DKGRAY),
            ButtonItem("Lorem", Color.LTGRAY, Color.DKGRAY),
            ButtonItem("Lorem Ipsum", Color.LTGRAY, Color.DKGRAY)
        )

        val adapter = ButtonAdapter(buttonList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SecondActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }


        val resultsRecyclerView: RecyclerView = findViewById(R.id.results_recycler_view)
        val resultsAdapter = ItemAdaptor(resultsList)
        resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SecondActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = resultsAdapter
        }

        val searchView = findViewById<SearchView>(R.id.secondSearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                val filtered = filterItems(resultsList, newText)
                resultsAdapter.setItems(filtered)
                dbManager.addNewPopularItemsToDatabase(filtered)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                val filtered = filterItems(resultsList, newText)
                resultsAdapter.setItems(filtered)
                return true
            }
        })
    }

    fun filterItems(items: List<Item>, query: String): List<Item> {
        return items.filter { it.title.contains(query, ignoreCase = true) }
    }


}