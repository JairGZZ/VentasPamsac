package com.jair.ventaspamsac.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jair.ventaspamsac.MainActivity
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.databinding.FragmentNotificationsBinding
import com.jair.ventaspamsac.ui.AuthViewModel
import com.jair.ventaspamsac.ui.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()
    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signOut()
        observeAuthState()
    }

    private fun signOut() {
        binding.btnCerrarSesion.setOnClickListener {
            authViewModel.signOut()
        }
    }

    private fun observeAuthState() {
        lifecycleScope.launch {
            authViewModel.authState.observe(viewLifecycleOwner) { result ->
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
        Toast.makeText(requireContext(), "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(requireContext(), AuthenticationActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}