package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coinnect.databinding.FragmentUserinfoBinding


class userinfoFragment : Fragment() {

    private var _binding: FragmentUserinfoBinding? = null
    private val binding get() = _binding!!

    private var userName = "Juan Pérez"
    private var userEmail = "juanperez@ejemplo.com"
    private var userInfo = "Soy un apasionado de las criptomonedas y la tecnología."
    private val profileImage = R.drawable.ic_myacc

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserinfoBinding.inflate(inflater, container, false)


        initializeUserInfo()


        binding.editUserInfoButton.setOnClickListener {

            enableEditing(true)
        }


        binding.saveUserInfoButton.setOnClickListener {

            saveUserInfo()

            enableEditing(false)
        }

        return binding.root
    }


    private fun initializeUserInfo() {
        binding.userName.text = userName
        binding.userEmail.text = userEmail
        binding.userInfo.text = userInfo
        binding.userProfileImage.setImageResource(profileImage)


        binding.editUserName.setText(userName)
        binding.editUserEmail.setText(userEmail)
        binding.editUserInfo.setText(userInfo)


        enableEditing(false)
    }


    private fun enableEditing(isEditable: Boolean) {
        if (isEditable) {

            binding.editUserName.visibility = View.VISIBLE
            binding.editUserEmail.visibility = View.VISIBLE
            binding.editUserInfo.visibility = View.VISIBLE

            binding.saveUserInfoButton.visibility = View.VISIBLE

            binding.editUserInfoButton.visibility = View.GONE
        } else {

            binding.editUserName.visibility = View.GONE
            binding.editUserEmail.visibility = View.GONE
            binding.editUserInfo.visibility = View.GONE

            binding.saveUserInfoButton.visibility = View.GONE

            binding.editUserInfoButton.visibility = View.VISIBLE
        }
    }


    private fun saveUserInfo() {

        userName = binding.editUserName.text.toString()
        userEmail = binding.editUserEmail.text.toString()
        userInfo = binding.editUserInfo.text.toString()


        binding.userName.text = userName
        binding.userEmail.text = userEmail
        binding.userInfo.text = userInfo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


