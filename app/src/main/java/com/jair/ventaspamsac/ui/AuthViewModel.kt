package com.jair.ventaspamsac.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.AuthRepository
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.domain.usecases.auth.SaveUserDataUseCase
import com.jair.ventaspamsac.domain.usecases.auth.SignInUseCase
import com.jair.ventaspamsac.domain.usecases.auth.SignOutUseCase
import com.jair.ventaspamsac.domain.usecases.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {

    private val _authState = MutableLiveData<AuthResult<Any>>(AuthResult.Loading)
    val authState: LiveData<AuthResult<Any>> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            _authState.value = signInUseCase(email, password)
        }
    }

    fun signUp(name: String, email: String, password: String) {
        Log.d("REGISTRO", "Intentando registrar con correo: [$email]")

        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            val result = signUpUseCase(name, email, password)
            _authState.value = result
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            _authState.value = signOutUseCase()
        }
    }
    fun saveUserData(user: User) {
        viewModelScope.launch {
            saveUserDataUseCase(user)
        }
    }
}