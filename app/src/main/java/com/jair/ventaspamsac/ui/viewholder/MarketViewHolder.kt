package com.jair.ventaspamsac.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.databinding.MarketItemBinding

class MarketViewHolder (private val binding: MarketItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(market: MarketEntity){
        binding.textMarketName.text = market.name
        binding.textMarketDistrictName.text = market.district
    }

}