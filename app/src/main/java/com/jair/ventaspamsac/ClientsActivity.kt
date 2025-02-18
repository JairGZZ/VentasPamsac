package com.jair.ventaspamsac

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jair.ventaspamsac.databinding.ActivityClientsBinding
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.ui.clients.ClientsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsActivity  : AppCompatActivity()  {

    private val clientsViewModel: ClientsViewModel by viewModels()
    private lateinit var binding: ActivityClientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id_market", -1)
        if (id != -1){
            clientsViewModel.getByMarket(id).observe(this,{

            })

        }


}



    }