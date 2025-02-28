package com.jair.ventaspamsac.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.databinding.ItemClientsBinding

class ClientViewHolder(val binding: ItemClientsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(client: ClientEntity){

        binding.textClientName.text = client.name
        binding.textClientLastName.text = client.lastName
        binding.textPhoneNumber.text = client.phone
        binding.textStoreNumber.text = client.storeNumber


    }
}