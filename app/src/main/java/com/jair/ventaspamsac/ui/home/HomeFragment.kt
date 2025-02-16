package com.jair.ventaspamsac.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jair.ventaspamsac.R
import com.jair.ventaspamsac.data.TypeOperation
import com.jair.ventaspamsac.databinding.ActivityMainBinding
import com.jair.ventaspamsac.databinding.FragmentHomeBinding
import com.jair.ventaspamsac.domain.items.MarketItem
import com.jair.ventaspamsac.ui.adapter.MarketAdapter
import com.jair.ventaspamsac.ui.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MarketAdapter.ItemClickListener {

    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: MarketAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private var currentQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSearchView()
        setupFloatingButton()
    }

    private fun setupRecyclerView() {
        adapter = MarketAdapter(this)
        binding.recyclerMarkets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setupObservers() {
        homeViewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
            adapter.filter(currentQuery)
            updateEmptyState()
        }
    }

    private fun setupSearchView() {
        binding.btnSearch.apply {
            queryHint = "Buscar mercado..."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    currentQuery = newText.orEmpty()
                    adapter.filter(currentQuery)
                    updateEmptyState()
                    return true
                }
            })
        }
    }

    private fun setupFloatingButton() {
        binding.floatingRegister.setOnClickListener {
            registerUpdateMarket(null, TypeOperation.REGISTER)
        }
    }

    private fun registerUpdateMarket(market: MarketItem?, type: TypeOperation) {
        val mDialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_market, null)
        val titleAlertNote = "Agregar Mercado"


        val mBuilder = AlertDialog.Builder(this.requireContext())
            .setView(mDialogView)
            .setTitle(titleAlertNote)
        val mAlertDialog = mBuilder.show()

        val edtMarketName = mDialogView.findViewById<EditText>(R.id.edtMarketName)
        val edtMarketDistrict = mDialogView.findViewById<EditText>(R.id.edtMarketDistrict)
        val btnCreate = mDialogView.findViewById<Button>(R.id.btnCreate)

        if (type == TypeOperation.UPDATE) {
            edtMarketName.setText(market?.name)
            edtMarketDistrict.setText(market?.district)
        }

        btnCreate.setOnClickListener {
            val name = edtMarketName.text.toString().trim()
            val district = edtMarketDistrict.text.toString().trim()

            if (name.isEmpty()) {
                edtMarketName.error = "El nombre es requerido"
                edtMarketName.requestFocus()
                return@setOnClickListener
            }

            if (district.isEmpty()) {
                edtMarketDistrict.error = "El distrito es requerido"
                edtMarketDistrict.requestFocus()
                return@setOnClickListener
            }

            mAlertDialog.dismiss()

            val marketItem = MarketItem(
                idMarket = 0,
                name = name,
                district = district
            )

            if (type == TypeOperation.UPDATE) {
                marketItem.idMarket = market!!.idMarket
                homeViewModel.updateMarket(marketItem)
            } else {
                homeViewModel.insertMarket(marketItem)
            }
        }
    }


    private fun updateEmptyState() {
        if (adapter.itemCount == 0) {
            binding.txtEmptyState.visibility = View.VISIBLE
            binding.recyclerMarkets.visibility = View.GONE
        } else {
            binding.txtEmptyState.visibility = View.GONE
            binding.recyclerMarkets.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(market: MarketItem) {

        Log.v("NOTA_ITEM", market.district)
        Log.v("NOTA_ITEM", market.name)
        Log.v("NOTA_ITEM", market.idMarket.toString())

        val bundle = Bundle().apply {
            putParcelable("market", market)
        }

        // Crear una instancia del fragmento de detalles
        val marketDetailsFragment = DashboardFragment().apply {
            arguments = bundle
        }

        // Reemplazar el fragmento actual con el fragmento de detalles
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_activity_main, marketDetailsFragment)
            addToBackStack(null) // Opcional: permite volver al fragmento anterior
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}