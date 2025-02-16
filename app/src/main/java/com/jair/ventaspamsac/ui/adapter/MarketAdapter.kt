package com.jair.ventaspamsac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.databinding.MarketItemBinding
import com.jair.ventaspamsac.domain.items.MarketItem
import com.jair.ventaspamsac.ui.viewholder.MarketViewHolder

class MarketAdapter(private val mItemListener: ItemClickListener) : RecyclerView.Adapter<MarketViewHolder>() {

    private var originalList = emptyList<MarketItem>()
    private var filteredList = emptyList<MarketItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val binding = MarketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarketViewHolder(binding)
    }

    override fun getItemCount(): Int = filteredList.size // Cambiado a filteredList

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val district = filteredList[position] // Usar filteredList aquí
        holder.bind(district)

        holder.itemView.setOnClickListener {

            mItemListener.onItemClick(filteredList[position])
        }
    }

    fun updateData(newItems: List<MarketItem>) {
        originalList = newItems
        filteredList = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { item ->
                item.name.contains(query, true) ||
                        item.district.contains(query, true)
            }
        }
        notifyDataSetChanged()
    }
    interface ItemClickListener {
        fun onItemClick(market: MarketItem)
    }
}
    
