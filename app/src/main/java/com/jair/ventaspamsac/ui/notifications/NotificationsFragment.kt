package com.jair.ventaspamsac.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jair.ventaspamsac.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

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

//        setupUI()
//        setupObservers()
    }

//    private fun setupUI() {
//        binding.btnInsert.setOnClickListener {
//            val districts: List<DistrictItem> = listOf(
//                DistrictItem(name = "Trujillo"),
//                DistrictItem(name = "Alto Trujillo"),
//                DistrictItem(name = "El Porvenir"),
//                DistrictItem(name = "La Esperanza"),
//                DistrictItem(name = "Florencia de Mora"),
//                DistrictItem(name = "Huanchaco"),
//                DistrictItem(name = "Laredo"),
//                DistrictItem(name = "Moche"),
//                DistrictItem(name = "Salaverry"),
//                DistrictItem(name = "Simbal"),
//                DistrictItem(name = "Víctor Larco Herrera"),
//                DistrictItem(name = "Santiago de Cao"),
//                DistrictItem(name = "Paiján")
//            )
//                viewModel.insertAllDistricts(districts)
//
//        }
//    }
//
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