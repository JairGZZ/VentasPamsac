package com.jair.ventaspamsac.ui.note

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jair.ventaspamsac.databinding.ActivityNoteBinding


class NoteActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityNoteBinding
//    private lateinit var adapter: NoteAdapter
    private var currentQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}