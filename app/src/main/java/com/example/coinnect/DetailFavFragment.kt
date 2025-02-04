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

        // Recuperar la criptomoneda seleccionada desde los argumentos
        arguments?.let {
            cryptocurrency = it.getParcelable("selectedCrypto")!!
        }

        // Configurar los datos de la criptomoneda en la vista
        binding.tvCryptoName.text = cryptocurrency.cryptoName
        binding.tvCryptoPrice.text = "Price: $${cryptocurrency.cryptoPrice}"

        // Mostrar las notas de la criptomoneda (si tiene)
        binding.etNotes.setText(cryptocurrency.notes)

        // Cargar la imagen de la criptomoneda con Glide
        Glide.with(this)
            .load(cryptocurrency.imageUrl)  // Aquí se usa la URL de la imagen
            .into(binding.cryptoImage)  // Suponiendo que tienes un ImageView con id "cryptoImage" en tu layout

        // Configurar el botón para guardar las notas
        binding.btnSaveNotes.setOnClickListener {
            val notes = binding.etNotes.text.toString()
            cryptocurrency.notes = notes

            Toast.makeText(requireContext(), "Notes saved!", Toast.LENGTH_SHORT).show()

            // Actualizar la lista de criptomonedas en el fragmento anterior
            val cryptoListFragment = parentFragmentManager.findFragmentByTag("CryptoListFragment") as CryptoListFragment?
            cryptoListFragment?.updateCryptoList(CryptocurrencyProvider.listaCryptos)

            // Volver al fragmento anterior
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
