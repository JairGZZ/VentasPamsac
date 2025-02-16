package com.jair.ventaspamsac.ui.notifications


import androidx.lifecycle.ViewModel
import com.jair.ventaspamsac.domain.usecases.district.GetAllDistrictsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getAllDistrictsUseCase: GetAllDistrictsUseCase
) : ViewModel() {




}