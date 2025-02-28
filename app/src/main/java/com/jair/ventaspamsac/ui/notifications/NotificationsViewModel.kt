package com.jair.ventaspamsac.ui.notifications


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
) : ViewModel() {

    private val _insertionStatus = MutableStateFlow<Boolean?>(null)

    val insertionStatus: StateFlow<Boolean?> = _insertionStatus.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()

    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()


}