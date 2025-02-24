package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.coinnect.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var selectedCrypto: Cryptocurrency? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        selectedCrypto = arguments?.getParcelable("selectedCrypto")

        selectedCrypto?.let {
            binding.cryptoName.text = it.cryptoName
            binding.cryptoPrice.text = getString(R.string.crypto_price, it.cryptoPrice)


            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.cryptoImage)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
