package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.coinnect.databinding.FragmentDetailFavBinding

class DetailFavFragment : Fragment() {

    private var _binding: FragmentDetailFavBinding? = null
    private val binding get() = _binding!!

    private lateinit var cryptocurrency: Cryptocurrency

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFavBinding.inflate(inflater, container, false)


        arguments?.let {
            cryptocurrency = it.getParcelable("selectedCrypto")!!
        }


        binding.tvCryptoName.text = cryptocurrency.cryptoName
        binding.tvCryptoPrice.text =   getString(R.string.crypto_price , cryptocurrency.cryptoPrice)


        binding.etNotes.setText(cryptocurrency.notes)


        Glide.with(this)
            .load(cryptocurrency.imageUrl)
            .into(binding.cryptoImage)


        binding.btnSaveNotes.setOnClickListener {
            val notes = binding.etNotes.text.toString()
            cryptocurrency.notes = notes

            Toast.makeText(requireContext(), getString(R.string.notassave), Toast.LENGTH_SHORT).show()


            val cryptoListFragment = parentFragmentManager.findFragmentByTag("CryptoListFragment") as CryptoListFragment?
            cryptoListFragment?.updateCryptoList(CryptocurrencyProvider.listaCryptos)


            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
