package com.example.coinnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.coinnect.databinding.FragmentMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        username = arguments?.getString("username")


        val adapter = MenuPagerAdapter(this, username)
        binding.viewPager.adapter = adapter



        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Info"
                    tab.setIcon(R.drawable.ic_crypto)
                }
                1 -> {
                    tab.text = "Crypto"
                    tab.setIcon(R.drawable.ic_chart)
                }
            }
        }.attach()

        binding.viewPager.isUserInputEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


