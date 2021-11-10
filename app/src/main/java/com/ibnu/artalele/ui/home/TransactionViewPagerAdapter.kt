package com.ibnu.artalele.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibnu.artalele.ui.home.pemasukan.all.AllIncomeContainerFragment
import com.ibnu.artalele.ui.home.pengeluaran.all.AllSpendingContainerFragment
import com.ibnu.artalele.utils.ConstValue.SPENDING

class TransactionViewPagerAdapter(
    fa: FragmentActivity,
    private val type: String,
    private val tabTitles: List<String>,
    private val year: Int
) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        return if (type == SPENDING) {
            AllSpendingContainerFragment(tabTitles[position], year)
        } else {
            AllIncomeContainerFragment(tabTitles[position], year)
        }
    }
}