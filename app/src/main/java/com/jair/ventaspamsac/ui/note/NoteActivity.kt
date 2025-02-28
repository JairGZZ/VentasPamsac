//package com.jair.ventaspamsac.ui.note
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import androidx.activity.viewModels
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.viewModelScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.jair.ventaspamsac.R
//import com.jair.ventaspamsac.data.TypeOperation
//import com.jair.ventaspamsac.databinding.ActivityNoteBinding
//import com.jair.ventaspamsac.databinding.DialogNoteBinding
//import com.jair.ventaspamsac.domain.items.NoteItem
//import com.jair.ventaspamsac.ui.adapter.NoteAdapter
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//
//
//@AndroidEntryPoint
//class NoteActivity : AppCompatActivity(), NoteAdapter.ItemClickListener {
//
//    private val noteViewModel: NoteViewModel by viewModels()
//    private lateinit var binding: ActivityNoteBinding
//    private lateinit var bindingD: DialogNoteBinding
//    private lateinit var adapter: NoteAdapter
//    private lateinit var nameClient :String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityNoteBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val clientId = intent.getIntExtra("id_client",-1)
//        nameClient = intent.getStringExtra("name_client")!!//
//
//
//
//        if (clientId != -1) {
//            noteViewModel.getNotesByClient(clientId).observe(this, {
//                if (it != null) {
//                    adapter.updateData(it)
//                }
//            })
//        }
//        setupRecyclerView()
//        setupFloatingButton()
//    }
//
//    private fun setupFloatingButton() {
//        binding.floatingRegister.setOnClickListener {
//            registerUpdateClient(null, TypeOperation.REGISTER)
//        }
//    }
//
//
//    private fun LocalDateTime.formatDateTime(): String =
//        this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
//
//    private fun setupRecyclerView() {
//        adapter = NoteAdapter(this)
//        binding.recyclerNotes.apply {
//            layoutManager = LinearLayoutManager(this@NoteActivity)
//            adapter = this@NoteActivity.adapter
//        }
//    }
//
//
//    private fun registerUpdateClient(note: NoteItem?, type: TypeOperation) {
//        // Infla el layout del diálogo y obtén su binding
//        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_note, null)
//        bindingD = DialogNoteBinding.bind(mDialogView) // Inicializa bindingD aquí
//
//        val titleAlertNote =
//            if (type == TypeOperation.UPDATE) "Actualizar Nota" else "Agregar Nota"
//        val mBuilder = AlertDialog.Builder(this)
//            .setView(mDialogView)
//            .setTitle(titleAlertNote)
//
//        val mAlertDialog = mBuilder.show()
//
//        // Si es una actualización, rellena los campos con los datos del cliente
//        if (type == TypeOperation.UPDATE) {
//            bindingD.edtTitleNote.setText(note?.title)
//            bindingD.edtDescriptionNote.setText(note?.content)
//        }
//
//        // Configura el botón de crear/actualizar
//        bindingD.btnCreate.setOnClickListener {
//            val title = bindingD.edtTitleNote.text.toString().trim()
//            val content = bindingD.edtDescriptionNote.text.toString().trim()
//            val date = LocalDateTime.now().formatDateTime()
//
//            // Validaciones
//            if (title.isEmpty()) {
//                bindingD.edtTitleNote.error = "El nombre es requerido"
//                bindingD.edtTitleNote.requestFocus()
//                return@setOnClickListener
//            }
//            if (content.isEmpty()) {
//                bindingD.edtDescriptionNote.error = "El apellido es requerido"
//                bindingD.edtDescriptionNote.requestFocus()
//                return@setOnClickListener
//            }
//
//
//            // Cierra el diálogo
//            mAlertDialog.dismiss()
//
//            // Crea el objeto note
//            val noteItem = NoteItem(
//                id = if (type == TypeOperation.UPDATE) note!!.id else 0,
//                title = title,
//                content = content,
//                date = date,
//                clientId = intent.getIntExtra("id_client", -1)
//            )
//
//            // Inserta o actualiza la nota según el tipo de operación
//            noteViewModel.viewModelScope.launch {
//                if (type == TypeOperation.UPDATE) {
//                    noteViewModel.updateNote(noteItem)
//                } else {
//                    noteViewModel.insertNote(noteItem)
//                }
//            }
//        }
//    }
//
//    override fun onClickBtnShare(noteItem: NoteItem) {
//        shareNotes(noteItem)
//    }
//
//    private fun shareNotes(note: NoteItem?) {
//        // Recopilar el texto de la nota
//        val notesText = StringBuilder()
//        if (note != null) {
//            notesText.append("${"Titulo: " + note.title}\n")
//            notesText.append("${"Contenido: "+note.content}\n")
//            notesText.append("${"Fecha y Hora: "+note.date}\n")
//            notesText.append("${"Nombre del Cliente: "+nameClient}\n")
//
//        }
//
//
//        // Crear un Intent para compartir el texto
//        val sendIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_TEXT, notesText.toString())
//            type = "text/plain"
////            setPackage("com.whatsapp")   solo si se quiere compartir por wasa
//
//        }
//
//        // Crear un selector para permitir al usuario elegir la aplicación
//        val shareIntent = Intent.createChooser(sendIntent, "Compartir Nota")
//        startActivity(shareIntent)
//    }
//}
//
//
//
//
