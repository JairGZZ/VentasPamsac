package com.jair.ventaspamsac.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jair.ventaspamsac.MainActivity
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.states.AuthResult

import com.jair.ventaspamsac.databinding.AtivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity(){
    private lateinit var binding: AtivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getEmailAndPassword()
        onClickBtnCrearCuenta()
        observeAuthState()

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
            authViewModel.signIn(email, password)

        }
    }

    private fun onClickBtnCrearCuenta() {
        binding.btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
    private fun observeAuthState() {
        lifecycleScope.launch {
            authViewModel.authState.observe(this@AuthenticationActivity) { result ->
                when (result) {
                    is AuthResult.Loading -> showLoading()
                    is AuthResult.Success<*> -> {
                        when (result.data) {
                            is User -> navigateToHome() // Caso de signIn/signUp


                        }
                    }

                    is AuthResult.Error -> showError(result.message)
                    AuthResult.SuccessWithoutData -> navigateToLogin()
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

    private fun reload() {
        TODO("Not yet implemented")
    }

//    private fun signInWithEmailPassword( email: String, password: String) {
//
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("TAG", "signInWithEmail:success")
//                    val user = firebaseAuth.currentUser
//
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//
//
//                }
//            }
//    }

}