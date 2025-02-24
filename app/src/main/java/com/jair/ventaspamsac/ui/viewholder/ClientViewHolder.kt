package com.jair.ventaspamsac.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.databinding.ItemClientsBinding
import com.jair.ventaspamsac.domain.items.ClientItem

class ClientViewHolder(val binding: ItemClientsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(client: ClientItem){

        binding.textClientName.text = client.name
        binding.textClientLastName.text = client.lastName
        binding.textPhoneNumber.text = client.phone
        binding.textStoreNumber.text = client.storeNumber


    }
}