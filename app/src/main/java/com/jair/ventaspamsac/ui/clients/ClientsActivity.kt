//package com.jair.ventaspamsac.ui.clients
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import androidx.activity.viewModels
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.SearchView
//import androidx.lifecycle.viewModelScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.jair.ventaspamsac.R
//import com.jair.ventaspamsac.data.TypeOperation
//import com.jair.ventaspamsac.databinding.ActivityClientsBinding
//import com.jair.ventaspamsac.databinding.DialogClientBinding
//import com.jair.ventaspamsac.databinding.ItemClientsBinding
//import com.jair.ventaspamsac.domain.items.ClientItem
//import com.jair.ventaspamsac.ui.adapter.ClientAdapter
//import com.jair.ventaspamsac.ui.note.NoteActivity
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//@AndroidEntryPoint
//class ClientsActivity : AppCompatActivity(), ClientAdapter.ItemClickListener {
//
//    private val clientsViewModel: ClientsViewModel by viewModels()
//    private lateinit var binding: ActivityClientsBinding
//    private lateinit var bindingD: DialogClientBinding
//    private lateinit var bindingItem: ItemClientsBinding
//    private var currentQuery = ""
//    private lateinit var adapter: ClientAdapter
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//
//        super.onCreate(savedInstanceState)
//        binding = ActivityClientsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val id = intent.getIntExtra("id_market", -1)
//        if (id != -1) {
//            clientsViewModel.getByMarket(id).observe(this, {
//                if (it != null) {
//                    adapter.updateData(it)
//                    adapter.filter(currentQuery)
//                    updateEmptyState()
//                }
//            })
//
//            setupRecyclerView()
//            setupSearchView()
//            setupFloatingButton()
//
//
//        }
//    }
//
//
//    override fun onCreateNoteClick(clientId: Int,name :String) {
//        val intent = Intent(this, NoteActivity::class.java)
//        intent.putExtra("id_client", clientId) // Pasa el ID del cliente
//        intent.putExtra("name_client", name) // Pasa el ID del cliente
//
//
//        startActivity(intent)
//    }
//
//    private fun setupRecyclerView() {
//        adapter = ClientAdapter(this)
//        binding.recyclerMarkets.apply {
//            layoutManager = LinearLayoutManager(this@ClientsActivity)
//            adapter = this@ClientsActivity.adapter
//        }
//    }
//
//
//    private fun updateEmptyState() {
//        if (adapter.itemCount == 0) {
//            binding.txtEmptyState.visibility = View.VISIBLE
//            binding.recyclerMarkets.visibility = View.GONE
//        } else {
//            binding.txtEmptyState.visibility = View.GONE
//            binding.recyclerMarkets.visibility = View.VISIBLE
//        }
//    }
//
//    private fun setupSearchView() {
//        binding.btnSearch.apply {
//            queryHint = "Buscar mercado..."
//            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean = false
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    currentQuery = newText.orEmpty()
//                    adapter.filter(currentQuery)
//                    updateEmptyState()
//                    return true
//                }
//            })
//        }
//    }
//
//    private fun setupFloatingButton() {
//        binding.floatingRegister.setOnClickListener {
//            registerUpdateClient(null, TypeOperation.REGISTER)
//        }
//    }
//
//    override fun onItemClick(client: ClientItem) {
//        registerUpdateClient(client, TypeOperation.UPDATE)
//
//    }
//
//
//    private fun registerUpdateClient(client: ClientItem?, type: TypeOperation) {
//        // Infla el layout del diálogo y obtén su binding
//        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_client, null)
//        bindingD = DialogClientBinding.bind(mDialogView) // Inicializa bindingD aquí
//
//        val titleAlertNote =
//            if (type == TypeOperation.UPDATE) "Actualizar Cliente" else "Agregar Cliente"
//        val mBuilder = AlertDialog.Builder(this)
//            .setView(mDialogView)
//            .setTitle(titleAlertNote)
//
//        val mAlertDialog = mBuilder.show()
//
//        // Si es una actualización, rellena los campos con los datos del cliente
//        if (type == TypeOperation.UPDATE) {
//            bindingD.edtName.setText(client?.name)
//            bindingD.edtLastName.setText(client?.lastName)
//            bindingD.edtPhone.setText(client?.phone)
//            bindingD.edtStoreNumber.setText(client?.storeNumber)
//        }
//
//        // Configura el botón de crear/actualizar
//        bindingD.btnCreate.setOnClickListener {
//            val name = bindingD.edtName.text.toString().trim()
//            val lastName = bindingD.edtLastName.text.toString().trim()
//            val phone = bindingD.edtPhone.text.toString().trim()
//            val storeNumber = bindingD.edtStoreNumber.text.toString().trim()
//
//            // Validaciones
//            if (name.isEmpty()) {
//                bindingD.edtName.error = "El nombre es requerido"
//                bindingD.edtName.requestFocus()
//                return@setOnClickListener
//            }
//            if (lastName.isEmpty()) {
//                bindingD.edtLastName.error = "El apellido es requerido"
//                bindingD.edtLastName.requestFocus()
//                return@setOnClickListener
//            }
//            if (phone.isEmpty()) {
//                bindingD.edtPhone.error = "El número de teléfono es requerido"
//                bindingD.edtPhone.requestFocus()
//                return@setOnClickListener
//            }
//
//            // Cierra el diálogo
//            mAlertDialog.dismiss()
//
//            // Crea el objeto ClientItem
//            val clientItem = ClientItem(
//                idClient = if (type == TypeOperation.UPDATE) client!!.idClient else 0,
//                name = name,
//                lastName = lastName,
//                phone = phone,
//                storeNumber = storeNumber,
//                idMarket = intent.getIntExtra("id_market", -1)
//            )
//
//            // Inserta o actualiza el cliente según el tipo de operación
//            clientsViewModel.viewModelScope.launch {
//                if (type == TypeOperation.UPDATE) {
//                    clientsViewModel.up(clientItem)
//                } else {
//                    clientsViewModel.insert(clientItem)
//                }
//            }
//        }
//    }
//
//}
//
//
//
