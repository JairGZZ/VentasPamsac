package com.jair.ventaspamsac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.databinding.MarketItemBinding
import com.jair.ventaspamsac.ui.viewholder.MarketViewHolder

class MarketAdapter(private val mItemListener: ItemClickListener) : RecyclerView.Adapter<MarketViewHolder>() {

    private var originalList = emptyList<MarketEntity>()
    private var filteredList = emptyList<MarketEntity>()

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

    fun updateData(newItems: List<MarketEntity>) {
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
        fun onItemClick(market: MarketEntity)
    }
}

