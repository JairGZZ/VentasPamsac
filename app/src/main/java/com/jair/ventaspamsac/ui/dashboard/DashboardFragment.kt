package com.jair.ventaspamsac.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jair.ventaspamsac.databinding.FragmentDashboardBinding
import com.jair.ventaspamsac.ui.notifications.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



//    private fun deleteAllDistrict() {
//        binding.btnEliminar.setOnClickListener {
//            viewModel.deleteAllDistrict()
//        }
//    }

//    private fun setupUI() {
//        binding.btnInsert.setOnClickListener {
//            val districtName = binding.etDistrictName.text.toString()
//
//            if (districtName.isNotBlank()) {
//                val newDistrict = DistrictItem(name = districtName)
//                viewModel.insertDistrict(newDistrict)
//            } else {
//                binding.tvStatus.text = "El nombre no puede estar vacío"
//            }
//        }
//    }

//    private fun setupObservers() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Estado de inserción
//                viewModel.insertionStatus.collectLatest { status ->
//                    status?.let {
//                        binding.tvStatus.text = if (it) "Éxito" else "Error"
//                        if (it) binding.etDistrictName.text?.clear()
//                    }
//                }
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Mensajes de error
//                viewModel.errorMessage.collectLatest { error ->
//                    binding.tvStatus.text = error
//                }
//            }
//        }
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}