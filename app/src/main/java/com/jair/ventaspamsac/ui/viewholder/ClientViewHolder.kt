package com.jair.ventaspamsac.ui.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.databinding.ClientItemBinding
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.domain.items.MarketItem

class ClientViewHolder(private val binding: ClientItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(client: ClientItem){

        binding.textClientName.text = client.name
        binding.textClientLastName.text = client.lastName
        binding.textPhoneNumber.text = client.phone
        binding.textStoreNumber.text = client.storeNumber




    }
}