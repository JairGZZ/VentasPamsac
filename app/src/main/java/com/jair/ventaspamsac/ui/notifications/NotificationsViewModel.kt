package com.jair.ventaspamsac.ui.notifications


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.domain.items.DistrictItem
import com.jair.ventaspamsac.domain.usecases.district.InsertAllDistrictsUseCase
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
    private val insertAllDistrictsUseCase: InsertAllDistrictsUseCase
) : ViewModel() {

    private val _insertionStatus = MutableStateFlow<Boolean?>(null)

    val insertionStatus: StateFlow<Boolean?> = _insertionStatus.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()

    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    fun insertAllDistricts(district : List<DistrictItem>) {
        viewModelScope.launch {
            try {
                insertAllDistrictsUseCase(district)
                _insertionStatus.value = true
            } catch (e: Exception) {
                _errorMessage.emit("Error: ${e.message}")
                _insertionStatus.value = false
            }

        }
    }
}