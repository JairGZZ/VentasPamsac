package com.jair.ventaspamsac.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.jair.ventaspamsac.MainActivity
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.FirestoreRepository
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.databinding.ActivityCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CreateAccountActivity () :AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getEmailAndPassword()
        onClickBtnIniciarSesion()
        observeAuthState()

    }
    private fun getEmailAndPassword(){
        binding.btnCrearCuenta.setOnClickListener {
            val email = binding.etCorreoRegistro.text.toString()
            val password = binding.etContrasenaRegistro.text.toString()
            val name = binding.etNombreCompleto.text.toString()

            if (name.isEmpty()) {
                binding.etNombreCompleto.error = "Ingrese su nombre"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etCorreoRegistro.error = "Ingrese su correo"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etContrasenaRegistro.error = "Ingrese su contraseña"
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.etContrasenaRegistro.error = "La contraseña debe tener al menos 6 caracteres"
                return@setOnClickListener
            }
            if (password != binding.etConfirmarContrasena.text.toString()) {
                binding.etConfirmarContrasena.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }

            authViewModel.signUp(name, email,password)
        }


    }

    private fun observeAuthState() {
        lifecycleScope.launch {
            authViewModel.authState.observe(this@CreateAccountActivity) { result ->
                when (result) {
                    is AuthResult.Loading -> showLoading()
                    is AuthResult.Success<*> -> {
                        Toast.makeText(this@CreateAccountActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        navigateToHome()
                    }
                    is AuthResult.Error -> showError(result.message)
                    else -> {}
                }
            }
        }
    }


    private fun showLoading() {

        Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    }

    private fun navigateToLogin() {

        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToHome() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onClickBtnIniciarSesion() {
        val spannableString = SpannableString("Ya tienes una cuenta? Inicia sesion aqui")
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
// Asignar el SpannableString al TextView
        binding.btnRegresarInicioSesion.text = spannableString
        binding.btnRegresarInicioSesion.setOnClickListener {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
        }

    }

}