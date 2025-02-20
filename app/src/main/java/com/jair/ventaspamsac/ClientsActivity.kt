package com.jair.ventaspamsac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jair.ventaspamsac.data.TypeOperation
import com.jair.ventaspamsac.databinding.ActivityClientsBinding
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.ui.adapter.ClientAdapter
import com.jair.ventaspamsac.ui.clients.ClientsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ClientsActivity : AppCompatActivity() {

    private val clientsViewModel: ClientsViewModel by viewModels()
    private lateinit var binding: ActivityClientsBinding
    private var currentQuery = ""
    private lateinit var adapter: ClientAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id_market", -1)
        if (id != -1) {
            clientsViewModel.getByMarket(id).observe(this, {
                if (it != null) {
                    adapter.updateData(it)
                    adapter.filter(currentQuery)
                    updateEmptyState()
            }
            })

            setupRecyclerView()
            setupSearchView()
            setupFloatingButton()


        }
    }
    private fun setupRecyclerView() {
        adapter = ClientAdapter()
        binding.recyclerMarkets.apply {
            layoutManager = LinearLayoutManager(this@ClientsActivity)
            adapter = this@ClientsActivity.adapter
        }
    }



    private fun updateEmptyState() {
        if (adapter.itemCount == 0) {
            binding.txtEmptyState.visibility = View.VISIBLE
            binding.recyclerMarkets.visibility = View.GONE
        } else {
            binding.txtEmptyState.visibility = View.GONE
            binding.recyclerMarkets.visibility = View.VISIBLE
        }
    }
    private fun setupSearchView() {
        binding.btnSearch.apply {
            queryHint = "Buscar mercado..."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    currentQuery = newText.orEmpty()
                    adapter.filter(currentQuery)
                    updateEmptyState()
                    return true
                }
            })
        }
    }
    private fun setupFloatingButton() {
        binding.floatingRegister.setOnClickListener {
            registerUpdateClient(null, TypeOperation.REGISTER)
        }
    }

    private fun registerUpdateClient(client: ClientItem?, type: TypeOperation) {
        val mDialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_client, null)
        val titleAlertNote = "Agregar Cliente"


        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(titleAlertNote)
        val mAlertDialog = mBuilder.show()

        val edtClientName = mDialogView.findViewById<EditText>(R.id.edtName)
        val edtClientLastName = mDialogView.findViewById<EditText>(R.id.edtLastName)
        val edtClientPhone = mDialogView.findViewById<EditText>(R.id.edtPhone)
        val edtClientStoreNumber = mDialogView.findViewById<EditText>(R.id.edtStoreNumber)
        val btnCreate = mDialogView.findViewById<Button>(R.id.btnCreate)

        if (type == TypeOperation.UPDATE) {
            edtClientName.setText(client?.name)
            edtClientLastName.setText(client?.lastName)
            edtClientPhone.setText(client?.phone)
            edtClientStoreNumber.setText(client?.storeNumber)
        }

        btnCreate.setOnClickListener {
            val name = edtClientName.text.toString().trim()
            val lastName = edtClientLastName.text.toString().trim()
            val phone = edtClientPhone.text.toString().trim()
            val storeNumber = edtClientStoreNumber.text.toString().trim()

            if (name.isEmpty()) {
                edtClientName.error = "El nombre es requerido"
                edtClientName.requestFocus()
                return@setOnClickListener
            }

            if (lastName.isEmpty()) {
                edtClientLastName.error = "El apellido es requerido"
                edtClientLastName.requestFocus()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                edtClientPhone.error = "El numero de telefono es requerido"
                edtClientPhone.requestFocus()
                return@setOnClickListener
            }


            mAlertDialog.dismiss()

            val clientItem = ClientItem(
                idClient = 0,
                name = name,
                lastName = lastName,
                phone = phone,
                storeNumber = storeNumber,
                idMarket = intent.getIntExtra("id_market", 2)

            )

//            if (type == TypeOperation.UPDATE) {
//                clientItem.idClient = client!!.idClient
//                clientsViewModel.up(marketItem)
//            } else {
                clientsViewModel.viewModelScope.launch {
                    clientsViewModel.insert(clientItem)

                }
//            }
        }
    }



}