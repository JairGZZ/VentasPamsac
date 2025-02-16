package com.jair.ventaspamsac.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.databinding.MarketItemBinding
import com.jair.ventaspamsac.domain.items.MarketItem

class MarketViewHolder (private val binding: MarketItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(market: MarketItem){
        binding.textMarketName.text = market.name
        binding.textMarketDistrictName.text = market.district
    }

}