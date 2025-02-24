package com.jair.ventaspamsac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.jair.ventaspamsac.databinding.ItemClientsBinding
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.ui.viewholder.ClientViewHolder
import com.jair.ventaspamsac.ui.viewholder.MarketViewHolder

class ClientAdapter ( private val mItemListener: ItemClickListener ) : RecyclerView.Adapter<ClientViewHolder>(){
    private var originalList = emptyList<ClientItem>()
    private var filteredList = emptyList<ClientItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding = ItemClientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClientViewHolder(binding)
    }

    override fun getItemCount(): Int = filteredList.size


    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val district = filteredList[position]
        holder.bind(district)

        holder.itemView.setOnClickListener {

            mItemListener.onItemClick(filteredList[position])
        }
        holder.binding.btnCreateNote.setOnClickListener {
            mItemListener.onCreateNoteClick(filteredList[position].idClient ,filteredList[position].name )
        }


    }

    fun updateData(newItems: List<ClientItem>) {
        originalList = newItems
        filteredList = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { item ->
                item.lastName.contains(query, true) ||
                        item.name.contains(query, true)
            }
        }
        notifyDataSetChanged()
    }
    interface ItemClickListener {
        fun onItemClick(client: ClientItem)
        fun onCreateNoteClick(clientId: Int, name:String)

    }
}