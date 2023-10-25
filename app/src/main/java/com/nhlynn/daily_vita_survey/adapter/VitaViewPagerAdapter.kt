package com.nhlynn.daily_vita_survey.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nhlynn.daily_vita_survey.fragment.AllergeiesFragment
import com.nhlynn.daily_vita_survey.fragment.ConcernFragment
import com.nhlynn.daily_vita_survey.fragment.DietFragment
import com.nhlynn.daily_vita_survey.fragment.ExposureFragment
import com.nhlynn.daily_vita_survey.fragment.WelcomeFragment

class VitaViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                WelcomeFragment.newInstance()
            }

            1 -> {
                ConcernFragment.newInstance()
            }

            2 -> {
                DietFragment.newInstance()
            }

            3 -> {
                AllergeiesFragment.newInstance()
            }

            else -> {
                ExposureFragment.newInstance()
            }
        }
    }
}