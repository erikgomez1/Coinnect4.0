package com.example.coinnect

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MenuPagerAdapter(fragment: Fragment, private val username: String?) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreditFragment().apply {
                arguments = Bundle().apply {
                    putString("username", username)
                }
            }
            1 -> InicioFragment()
            else -> InicioFragment()
        }
    }
}

