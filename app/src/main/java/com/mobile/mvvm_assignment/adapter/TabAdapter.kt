package com.mobile.mvvm_assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobile.mvvm_assignment.fragment.CharactersFragment
import com.mobile.mvvm_assignment.fragment.StaffFragment
import com.mobile.mvvm_assignment.fragment.StudentsFragment


class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharactersFragment()
            }

            1 -> {
                StaffFragment()
            }

            2 -> {
                StudentsFragment()
            }

            else -> {
                Fragment()
            }
        }
    }

}