package com.ibnu.artalele.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ibnu.artalele.R
import com.ibnu.artalele.ui.home.pemasukan.IncomeFragment
import com.ibnu.artalele.ui.home.pengeluaran.SpendingFragment

class HomeViewPagerAdapter(fm: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = intArrayOf(
        R.string.pemasukan,
        R.string.pengeluaran
    )

    override fun getCount(): Int = tabTitles.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = IncomeFragment()
            }
            1 -> {
                fragment = SpendingFragment()
            }
        }

        return fragment as Fragment;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitles[position])
    }
}