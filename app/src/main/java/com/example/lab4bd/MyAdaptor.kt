package com.example.lab4bd


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Context, private var dataList: List<SelectedPopularItem>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.popular_products_item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = dataList[position]
            holder.bindData(item)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        fun setItems(newItems: List<SelectedPopularItem>) {
            dataList = newItems
            notifyDataSetChanged()
        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val titleTextView: TextView = itemView.findViewById(R.id.title_text)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.description_text)

        fun bindData(item: SelectedPopularItem) {
            imageView.setImageResource(item.src)
            titleTextView.text = item.itemTitle
            descriptionTextView.text = item.itemDescription
        }
    }

}
