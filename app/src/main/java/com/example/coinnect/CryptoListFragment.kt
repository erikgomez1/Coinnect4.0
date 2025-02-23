package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinnect.adapter.CryptoAdapter
import com.example.coinnect.databinding.FragmentCryptoListBinding

class CryptoListFragment : Fragment() {

    private var _binding: FragmentCryptoListBinding? = null
    private val binding get() = _binding!!

    private val cryptocurrencies = CryptocurrencyProvider.listaCryptos

    private var showFavorites = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoListBinding.inflate(inflater, container, false)

        initRecyclerView()
        initUserInfoButton()
        initFavoritesButton()
        initVolverButton()

        return binding.root
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerViewCryptos.layoutManager = manager

        val adapter = CryptoAdapter(cryptocurrencies, { cryptocurrency ->
            onItemSelected(cryptocurrency)
        }, { cryptocurrency, position ->
            onFavoriteSelected(cryptocurrency, position)
        })
        binding.recyclerViewCryptos.adapter = adapter
    }

    private fun onItemSelected(cryptocurrency: Cryptocurrency) {
        val bundle = Bundle()
        bundle.putParcelable("selectedCrypto", cryptocurrency)

        if (cryptocurrency.isFavorite) {
            findNavController().navigate(R.id.action_cryptoListFragment_to_detailFavFragment, bundle)
        } else {
            findNavController().navigate(R.id.action_cryptoListFragment_to_detailFragment, bundle)
        }
    }

    private fun onFavoriteSelected(cryptocurrency: Cryptocurrency, position: Int) {
        cryptocurrency.isFavorite = !cryptocurrency.isFavorite

        Toast.makeText(
            requireContext(),
            if (cryptocurrency.isFavorite) getString(R.string.addfav ,cryptocurrency.cryptoName)
            else getString(R.string.delfav , cryptocurrency.cryptoName),
            Toast.LENGTH_SHORT
        ).show()


        (binding.recyclerViewCryptos.adapter as CryptoAdapter).updateItem(position)
    }

    private fun initUserInfoButton() {
        val userInfoButton: Button = binding.root.findViewById(R.id.btnMiCuenta)
        userInfoButton.setOnClickListener {
            findNavController().navigate(R.id.action_cryptoListFragment_to_userinfoFragment)
        }
        userInfoButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_myacc, 0, 0)
    }

    private fun initFavoritesButton() {
        val favoritesButton: Button = binding.root.findViewById(R.id.btnFavoritos)
        favoritesButton.setOnClickListener {
            showFavorites = !showFavorites

            val filteredList = if (showFavorites) {
                cryptocurrencies.filter { it.isFavorite }
            } else {
                cryptocurrencies
            }

            (binding.recyclerViewCryptos.adapter as CryptoAdapter).updateCryptoList(filteredList)

            val message = if (showFavorites) "Mostrando solo favoritos" else "Mostrando todas las criptomonedas"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        favoritesButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_filled, 0, 0)
    }

    private fun initVolverButton() {
        val volverButton: Button = binding.root.findViewById(R.id.btnVolver)
        volverButton.setOnClickListener {
            findNavController().popBackStack()
        }
        volverButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_back, 0, 0)
    }

    fun updateCryptoList(updatedList: List<Cryptocurrency>) {
        (binding.recyclerViewCryptos.adapter as CryptoAdapter).updateCryptoList(updatedList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
