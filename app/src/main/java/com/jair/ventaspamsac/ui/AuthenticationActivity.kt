package com.jair.ventaspamsac.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.jair.ventaspamsac.MainActivity
import com.jair.ventaspamsac.databinding.AtivityLoginBinding


class AuthenticationActivity : AppCompatActivity(){
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: AtivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        getEmailAndPassword()
        onClickBtnCrearCuenta()
    }

    private fun getEmailAndPassword(){
        binding.btnIngresar.setOnClickListener {
            val email = binding.etCorreo.text.toString()
            val password = binding.etContrasena.text.toString()
            if (email.isEmpty()){
                binding.etCorreo.error = "Ingrese su correo"
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.etContrasena.error = "Ingrese su contraseña"
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.etContrasena.error = "La contraseña debe tener al menos 6 caracteres"
                return@setOnClickListener
            }
            signInWithEmailPassword(email, password)
        }
    }

    private fun onClickBtnCrearCuenta() {
        binding.btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun reload() {
        TODO("Not yet implemented")
    }

    private fun signInWithEmailPassword( email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = firebaseAuth.currentUser

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()


                }
            }
    }

}