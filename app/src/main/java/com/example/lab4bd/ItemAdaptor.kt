package com.example.lab4bd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class ItemAdaptor( private var items: List<Any>) :  RecyclerView.Adapter<ItemAdaptor.ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.results_list_item1, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.getPopularItemButtonView().setOnClickListener {
            // Сховати кнопку
            holder.getPopularItemButtonView().visibility = View.GONE
            // Показати контейнер
            holder.getResultsListQuantitySelector().visibility = View.VISIBLE
        }

        holder.getAddQuantityImageView().setOnClickListener {
            holder.increaseQuantity()
        }

        holder.getMinusQuantityImageView().setOnClickListener {
            holder.decreaseQuantity()
        }
        val firstItem = item as Item
        holder.bindData(firstItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun setItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.results_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.results_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.results_description)
        private val priceTextView: TextView = itemView.findViewById(R.id.results_price)
        private val popularItemButton: AppCompatButton = itemView.findViewById(R.id.popular_item_button)
        private val resultsListQuantitySelector: LinearLayout = itemView.findViewById(R.id.results_list_quantity_selector)
        private val quantityView: TextView = itemView.findViewById(R.id.results_quantity)
        private val addQuantityImageView: ImageView = itemView.findViewById(R.id.add_quantity)
        private val minusQuantityImageView: ImageView = itemView.findViewById(R.id.minus_quantity)
        private var quantity: Int = 1
        fun getPopularItemButtonView():AppCompatButton{
            return popularItemButton
        }
        fun getResultsListQuantitySelector():LinearLayout{
            return resultsListQuantitySelector
        }
        fun getAddQuantityImageView():ImageView{
           return addQuantityImageView
        }
        fun getMinusQuantityImageView():ImageView{
            return minusQuantityImageView
        }
        fun increaseQuantity() {
            quantity++
            updateQuantityDisplay()
        }
        fun decreaseQuantity() {
            if(quantity==1){
                popularItemButton.visibility = View.VISIBLE
                resultsListQuantitySelector.visibility = View.GONE
                return
            }
            quantity--
            updateQuantityDisplay()
        }

        private fun updateQuantityDisplay() {
            quantityView.text = quantity.toString()
        }


        fun bindData(item: Item) {
            imageView.setImageResource(item.src)
            titleTextView.text = item.title
            descriptionTextView.text = item.description
            priceTextView.text = item.price
        }
    }

}