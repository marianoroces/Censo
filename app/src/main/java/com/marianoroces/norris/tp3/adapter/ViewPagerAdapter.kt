package com.marianoroces.norris.tp3.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.marianoroces.norris.tp3.fragment.DistributionFragment
import com.marianoroces.norris.tp3.fragment.PeopleFragment
import com.marianoroces.norris.tp3.fragment.PovertyFragment
import com.marianoroces.norris.tp3.fragment.UnemployedFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PeopleFragment()
            1 -> UnemployedFragment()
            2 -> PovertyFragment()
            3 -> DistributionFragment()
            else -> Fragment()
        }
    }
}