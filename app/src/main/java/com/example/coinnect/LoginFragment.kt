package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coinnect.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val usernameEditText = binding.etUsername
        val loginButton = binding.btnLogin

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            if (username.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("username", username)


                findNavController().navigate(R.id.action_loginFragment_to_menuFragment, bundle)
            } else {
                usernameEditText.error = "Username is required"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

