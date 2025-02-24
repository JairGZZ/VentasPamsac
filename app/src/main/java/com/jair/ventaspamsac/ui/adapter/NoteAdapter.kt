package com.jair.ventaspamsac.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jair.ventaspamsac.databinding.ItemNoteBinding
import com.jair.ventaspamsac.domain.items.NoteItem
import com.jair.ventaspamsac.ui.viewholder.NoteViewHolder

class NoteAdapter (private val mItemListener: ItemClickListener): RecyclerView.Adapter<NoteViewHolder>() {
    private var notesList: List<NoteItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = notesList[position]
        holder.bind(note)
        holder.binding.btnShare.setOnClickListener {
            mItemListener.onClickBtnShare(note)
        }

    }
    fun updateData(newItems: List<NoteItem>) {
        notesList = newItems
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickBtnShare(noteItem: NoteItem)
    }


}