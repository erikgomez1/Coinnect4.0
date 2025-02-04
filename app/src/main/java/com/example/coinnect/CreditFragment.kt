package com.example.coinnect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinnect.databinding.FragmentCreditBinding

class CreditFragment : Fragment() {

    private var _binding: FragmentCreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreditBinding.inflate(inflater, container, false)


        val user = arguments?.getString("username")


        binding.tvUser.text = getString(R.string.acerca, user)


        binding.btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("supportcoinnect@coinnect.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Consulta de la App Coinnect")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Selecciona una aplicación para enviar un correo"))
            } else {
                Toast.makeText(requireContext(), "No existe aplicación para enviar el correo", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

