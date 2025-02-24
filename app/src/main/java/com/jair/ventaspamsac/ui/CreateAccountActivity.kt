package com.jair.ventaspamsac.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.jair.ventaspamsac.MainActivity
import com.jair.ventaspamsac.databinding.ActivityCreateAccountBinding

class CreateAccountActivity :AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        getEmailAndPassword()
        onClickBtnIniciarSesion()

    }
    private fun getEmailAndPassword() {
        binding.btnCrearCuenta.setOnClickListener {
            val email = binding.etCorreoRegistro.text.toString()
            val password = binding.etContrasenaRegistro.text.toString()
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

            createAccountWithEmailPassword(email, password)
        }
    }
    private fun createAccountWithEmailPassword(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "Fallo el inicio de sesion", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Fallo la creacion de cuenta, vuelva a intentarlo",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

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